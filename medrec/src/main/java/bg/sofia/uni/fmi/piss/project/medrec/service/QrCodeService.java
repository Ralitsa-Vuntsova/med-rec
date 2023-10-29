package bg.sofia.uni.fmi.piss.project.medrec.service;

import bg.sofia.uni.fmi.piss.project.medrec.dto.DecodedQrCodeResponseDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.QrCodeResponseDto;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.CouldNotDecodeException;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.ExternalServiceNotAvailableException;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.QrCodeNotFoundException;
import bg.sofia.uni.fmi.piss.project.medrec.model.QrCodeEntity;
import bg.sofia.uni.fmi.piss.project.medrec.repository.QrCodeRepository;
import bg.sofia.uni.fmi.piss.project.medrec.service.qr.QrCode;
import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QrCodeService {
    private final QrCodeRepository qrCodeRepository;
    private final ModelMapper modelMapper;

    public DecodedQrCodeResponseDto decodeQrCode(Long qrCodeId)
            throws QrCodeNotFoundException, CouldNotDecodeException, ExternalServiceNotAvailableException {
        String filename = getFileName(qrCodeId);

        String decodedQrCode = sendQrCodeForDecoding(filename);

        if (decodedQrCode != null && decodedQrCode.equals("Could not decode QR code")) {
            throw new CouldNotDecodeException("Could not decode QR code");
        }

        return toQrCodeResponseDto(decodedQrCode, qrCodeId);
    }

    public List<QrCodeResponseDto> getQrCodesForUser(Long userId) {
        Optional<List<QrCodeEntity>> qrCodes = qrCodeRepository.findAllByUserId(userId);
        List<QrCodeResponseDto> qrCodesResponse = new ArrayList<>();

        if (qrCodes.isEmpty()) {
            return qrCodesResponse;
        }

        for (QrCodeEntity qrCodeEntity : qrCodes.get()) {
            QrCodeResponseDto qrCodeResponse = modelMapper.map(qrCodeEntity, QrCodeResponseDto.class);
            qrCodeResponse.setPathName(qrCodeEntity.getFilename());

            qrCodesResponse.add(qrCodeResponse);
        }
        return qrCodesResponse;
    }

    private String getFileName(Long qrCodeId) throws QrCodeNotFoundException {
        Optional<QrCodeEntity> qrCode = qrCodeRepository.findById(qrCodeId);
        if (qrCode.isEmpty()) {
            throw new QrCodeNotFoundException("There is no qr code with this id.");
        }
        return qrCode.get().getFilename();
    }

    private static String sendQrCodeForDecoding(String filename) throws ExternalServiceNotAvailableException {
        try (SocketChannel socketChannel = SocketChannel.open();
             BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, "UTF-8"));
             PrintWriter printWriter = new PrintWriter(Channels.newWriter(socketChannel, "UTF-8"), true);) {

            Dotenv dotenv = Dotenv.configure().load();
            String hostname = dotenv.get("QRCODE_SERVICE_HOSTNAME");
            int port = Integer.parseInt(dotenv.get("QRCODE_SERVICE_PORT"));
            socketChannel.connect(new InetSocketAddress(hostname, port));

            System.out.println("Sending filename <" + filename + "> to the qr code decoder...");
            printWriter.println(filename);
            String reply = reader.readLine();

            System.out.println("The server responded with: \n" + reply);
            return reply;
        } catch (IOException e) {
            throw new ExternalServiceNotAvailableException("Qr code decoder not available. Try again later.");
        }
    }

    private DecodedQrCodeResponseDto toQrCodeResponseDto(String decodedQrCode, Long qrCodeId) {
        Gson gson = new Gson();
        QrCode qr = gson.fromJson(decodedQrCode, QrCode.class);

        DecodedQrCodeResponseDto decodedQr = modelMapper.map(qr.getPrescription(), DecodedQrCodeResponseDto.class);
        decodedQr.setId(qrCodeId);

        return decodedQr;
    }
}
