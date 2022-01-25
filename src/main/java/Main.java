import documentGenerator.DocumentCompany;
import documentGenerator.DocumentTitleBlock;
import documentGenerator.PdfDocumentGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String pathToInputFile = "./src/main/resources/A4-first-p.pdf";
        String pathToTwoBigPagesPdf = "./src/main/resources/A4big2.pdf";
        String pathToTwoSmallPagesPdf = "./src/main/resources/A4Small2.pdf";
        String pathToCoverTemplate = "./src/main/resources/PDF_Templates/A4CoverTemplate.pdf";
        String output1 = "./target/filled_A4-first-p.pdf";
        String output2 = "./target/filled_A4big2.pdf";
        String output3 = "./target/filled_A4Small2.pdf";
        String output4 = "./target/filled_A4Cover.pdf";
        byte[] inputTemplate = Files.readAllBytes(Paths.get(pathToInputFile));
        byte[] inputTemplate2 = Files.readAllBytes(Paths.get(pathToTwoBigPagesPdf));
        byte[] inputTemplate3 = Files.readAllBytes(Paths.get(pathToTwoSmallPagesPdf));
        byte[] inputTemplate4 = Files.readAllBytes(Paths.get(pathToCoverTemplate));

        PdfDocumentGenerator generator1 = new PdfDocumentGenerator(inputTemplate);
        Files.write(Paths.get(output1), generator1.getFilledTableDocumentTitleBlock(getStampForDocs()).getPdfDocumentInBytes());

        PdfDocumentGenerator generator2 = new PdfDocumentGenerator(inputTemplate2);
        Files.write(Paths.get(output2), generator2.getFilledBlueprintDocumentTitleBlock(getStampForDocs()).getPdfDocumentInBytes());

        PdfDocumentGenerator generator3 = new PdfDocumentGenerator(inputTemplate3);
        Files.write(Paths.get(output3), generator3.getFilledTextDocumentTitleBlock(getStampForDocs()).getPdfDocumentInBytes());

        PdfDocumentGenerator generator4 = new PdfDocumentGenerator(inputTemplate4);
        Files.write(Paths.get(output4), generator4.getFilledCoverDocument(getStampForCover()).getPdfDocumentInBytes());
    }

    public static DocumentTitleBlock getStampForDocs() throws IOException {
        String blueprintName = "Пояснительная записка";
        String designerName = "Иванов";
        String supervisorName = "Петров";
        String controllerName = "Сидоров";
        String chiefEngineerName = "Лавочкин";
        byte[] companyLogoImage = Files.readAllBytes(Paths.get("./src/main/resources/CompanyLogo/logo-stamp-color.jpg"));
        byte[] signature1 = Files.readAllBytes(Paths.get("./src/main/resources/Signatures/signature1.png"));
        byte[] signature2 = Files.readAllBytes(Paths.get("./src/main/resources/Signatures/signature2.png"));
        byte[] signature3 = Files.readAllBytes(Paths.get("./src/main/resources/Signatures/signature3.png"));
        byte[] signature4 = Files.readAllBytes(Paths.get("./src/main/resources/Signatures/signature4.png"));
        DocumentCompany company = new DocumentCompany("ООО Рога и копыта", companyLogoImage, "Воронеж", "Генеральный директор", "Скамейкин Ф.А.");
        String stageCode = "Р";
        String blueprintCode = "181022/ДР-МУП-ОС.1-Э5-2.РП1";
        String projectName = "Размеры 1,8 и 2,5 не допускаются, если чертёж выполняется карандашом.";
        String objectAddress = "г.Воронеж, ул.Челюскинцев, д.145";
        String releaseDate = "02.22";
        int volumeNumber = 1;
        String volumeName = "Система видеонаблюдения";

        return new DocumentTitleBlock(blueprintCode,
                blueprintName,
                designerName,
                signature1,
                supervisorName,
                signature2,
                controllerName,
                signature3,
                chiefEngineerName,
                signature4,
                stageCode,
                projectName,
                objectAddress,
                releaseDate,
                company,
                volumeNumber,
                volumeName);
    }

    public static DocumentTitleBlock getStampForCover() throws IOException {
        DocumentCompany company = new DocumentCompany("ООО Рога и копыта", null, "Воронеж", "Генеральный директор", "Скамейкин Ф.А.");
        String stage = "Рабочая документация";
        String documentCode = "181022/ДР-МУП-ОС.1";
        String projectName = "Создание системы обнаружения и фиксации несанкционированного пересечения охранного периметра в ООО «Минудобрения»";
        String objectAddress = "г.Воронеж, ул.Челюскинцев, д.145";
        String releaseDate = "2021";
        int volumeNumber = 1;
        String volumeName = "Система обнаружения и фиксации несанкционированного пересечения охранного периметра в ООО «Минудобрения»";

        return new DocumentTitleBlock(documentCode,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                stage,
                projectName,
                objectAddress,
                releaseDate,
                company,
                volumeNumber,
                volumeName);
    }

}
