import blueprintPdfCompiler.DocumentCompany;
import blueprintPdfCompiler.DocumentTitleBlock;
import blueprintPdfCompiler.PdfDocumentGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathToInputFile = "./src/main/resources/A4-first-p.pdf";
        String pathToTwoBigPagesPdf = "./src/main/resources/A4big2.pdf";
        String pathToTwoSmallPagesPdf = "./src/main/resources/A4Small2.pdf";
        File inputTemplate = new File(pathToInputFile);
        File inputTemplate2 = new File(pathToTwoBigPagesPdf);
        File inputTemplate3 = new File(pathToTwoSmallPagesPdf);

        PdfDocumentGenerator blueprint = new PdfDocumentGenerator(inputTemplate);
        blueprint.getFilledTableDocumentTitleBlock(getStamp());

        PdfDocumentGenerator blueprint2 = new PdfDocumentGenerator(inputTemplate2);
        blueprint2.getFilledBlueprintDocumentTitleBlock(getStamp());

        PdfDocumentGenerator blueprint3 = new PdfDocumentGenerator(inputTemplate3);
        blueprint3.getFilledTextDocumentTitleBlock(getStamp());
    }

    public static DocumentTitleBlock getStamp() throws IOException {
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
        DocumentCompany company = new DocumentCompany("ООО Рога и копыта", companyLogoImage);
        String stageCode = "Р";
        String blueprintCode = "181022/ДР-МУП-ОС.1-Э5-2.РП1";
        String projectName = "Размеры 1,8 и 2,5 не допускаются, если чертёж выполняется карандашом. Nhfn ndfjn jdnfj jndfjndfjn jndjfnj";
        String objectAddress = "г.Воронеж, ул.Челюскинцев, д.145 ывываы ывавыаы ыаываыв";
        String releaseDate = "02.22";

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
                company);
    }

}
