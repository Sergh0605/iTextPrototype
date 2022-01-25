package documentGenerator;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.List;

import static documentGenerator.PdfUtils.millimetersToPoints;

public class PdfDocumentGenerator {
    private final byte[] pdfDocumentInBytes;
    private final String GostA = "./src/main/resources/font/GOST_type_A_1.ttf";
    private final PdfFont mainFont = PdfFontFactory.createFont(GostA, "Identity-H", PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

    public PdfDocumentGenerator(byte[] pdfDocumentInBytes) throws Exception {
        this.pdfDocumentInBytes = pdfDocumentInBytes;
    }

    private Map<Rectangle, Integer> getFieldAreasInTitleBlockOnPageOfDocument(PdfDocument pdfDocument, DocumentRelativeFieldAreas relativeFieldArea) {
        Map<Rectangle, Integer> fieldAreas = new HashMap<>();
        int pageCount = pdfDocument.getNumberOfPages();
        if (pageCount > 0) {
            for (int i = 1; i <= pageCount; i++) {
                Rectangle pageSize = pdfDocument.getPage(i).getPageSize();
                if (i == 1 && relativeFieldArea.isVisibleOnFirstPage()) {
                    fieldAreas.put(getFieldAreaInDocument(pageSize, relativeFieldArea), i);
                }
                if (i > 1 && relativeFieldArea.isVisibleOnSecondPage()) {
                    fieldAreas.put(getFieldAreaInDocument(pageSize, relativeFieldArea), i);
                }
            }
        }
        return fieldAreas;
    }

    private Rectangle getFieldAreaInDocument(Rectangle pageSize, DocumentRelativeFieldAreas relativeFieldArea) {
        Rectangle fieldArea;
        if (relativeFieldArea.equals(DocumentRelativeFieldAreas.AdditionalCode)) {
            fieldArea = new Rectangle(
                    relativeFieldArea.getX(),
                    pageSize.getTop() + relativeFieldArea.getY(),
                    relativeFieldArea.getWidth(),
                    relativeFieldArea.getHeight());
        } else {
            fieldArea = new Rectangle(
                    pageSize.getRight() + relativeFieldArea.getX(),
                    relativeFieldArea.getY(),
                    relativeFieldArea.getWidth(),
                    relativeFieldArea.getHeight());
        }
        return fieldArea;
    }

    public PdfDocumentGenerator getFilledBlueprintDocumentTitleBlock(@NotNull DocumentTitleBlock inputStamp) throws Exception {
        return getFilledDocument(getRelativeFieldAreasWithDataForBlueprintDocumentTitleBlock(inputStamp));
    }

    public PdfDocumentGenerator getFilledTextDocumentTitleBlock(@NotNull DocumentTitleBlock inputStamp) throws Exception {
        return getFilledDocument(getRelativeFieldAreasWithDataForTextDocumentTitleBlock(inputStamp));
    }

    public PdfDocumentGenerator getFilledTableDocumentTitleBlock(@NotNull DocumentTitleBlock inputStamp) throws Exception {
        return getFilledDocument(getRelativeFieldAreasWithDataForTableDocumentTitleBlock(inputStamp));
    }

    public PdfDocumentGenerator getFilledCoverDocument(@NotNull DocumentTitleBlock inputStamp) throws Exception {
        return getFilledDocument(getRelativeFieldAreasWithDataForCoverDocument(inputStamp));
    }

    private PdfDocumentGenerator getFilledDocument(Map<DocumentRelativeFieldAreas, Object> relativeFieldAreasWithData) throws Exception {
        try (PdfReader reader = new PdfReader(new ByteArrayInputStream(pdfDocumentInBytes));
             ByteArrayOutputStream os = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(os);
             PdfDocument pdfDoc = new PdfDocument(reader, writer);
             Document document = new Document(pdfDoc)) {
            List<Paragraph> filledFieldsOfDocument = new ArrayList<>();
            relativeFieldAreasWithData.forEach((x, y) -> {
                filledFieldsOfDocument.addAll(getTitleBlockFilledFields(y, x, pdfDoc));
            });
            filledFieldsOfDocument.forEach(document::add);
            document.close();
            return new PdfDocumentGenerator(os.toByteArray());
        } catch (Exception e) {
            throw e;
        }

    }

    private Map<DocumentRelativeFieldAreas, Object> getRelativeFieldAreasWithDataForBlueprintDocumentTitleBlock(DocumentTitleBlock stamp) {
        Map<DocumentRelativeFieldAreas, Object> relativeFieldAreasWithData = getRelativeFieldAreasWithDataForTableDocumentTitleBlock(stamp);
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.AdditionalCode, stamp.getDocumentCode());
        return relativeFieldAreasWithData;
    }

    private Map<DocumentRelativeFieldAreas, Object> getRelativeFieldAreasWithDataForTextDocumentTitleBlock(DocumentTitleBlock stamp) {
        Map<DocumentRelativeFieldAreas, Object> relativeFieldAreasWithData = new HashMap<>();
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallMainCodeInFirstPage, stamp.getDocumentCode());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.MainCodeInSecondPage, stamp.getDocumentCode());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.AdditionalCode, stamp.getDocumentCode());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallDocumentName, stamp.getDocumentName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallPageNumberInFirstPage, "");
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.PageNumberInSecondPage, "");
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallTotalPagesCountInFirstPage, "");
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallDesigner, stamp.getDesignerName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.Controller, stamp.getControllerName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ChiefEngineer, stamp.getChiefEngineerName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallSupervisor, stamp.getSupervisorName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallStage, stamp.getStage());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CompanyName, stamp.getCompany());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallDesignerDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallSupervisorDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ChiefEngineerDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ControllerDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallDesignerSign, stamp.getDesignerSign());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SmallSupervisorSign, stamp.getSupervisorSign());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ChiefEngineerSign, stamp.getChiefEngineerSign());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ControllerSign, stamp.getControllerSign());
        return relativeFieldAreasWithData;
    }

    private Map<DocumentRelativeFieldAreas, Object> getRelativeFieldAreasWithDataForTableDocumentTitleBlock(DocumentTitleBlock stamp) {
        Map<DocumentRelativeFieldAreas, Object> relativeFieldAreasWithData = new HashMap<>();
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.MainCodeInFirstPage, stamp.getDocumentCode());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.MainCodeInSecondPage, stamp.getDocumentCode());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.DocumentName, stamp.getDocumentName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.Address, stamp.getObjectAddress());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ProjectName, stamp.getProjectName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.PageNumberInFirstPage, "");
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.PageNumberInSecondPage, "");
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.TotalPagesCountInFirstPage, "");
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.Designer, stamp.getDesignerName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.Controller, stamp.getControllerName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ChiefEngineer, stamp.getChiefEngineerName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.Supervisor, stamp.getSupervisorName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.Stage, stamp.getStage());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CompanyName, stamp.getCompany());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.DesignerDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SupervisorDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ChiefEngineerDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ControllerDate, stamp.getReleaseDate());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.DesignerSign, stamp.getDesignerSign());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.SupervisorSign, stamp.getSupervisorSign());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ChiefEngineerSign, stamp.getChiefEngineerSign());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.ControllerSign, stamp.getControllerSign());
        return relativeFieldAreasWithData;
    }

    private Map<DocumentRelativeFieldAreas, Object> getRelativeFieldAreasWithDataForCoverDocument(DocumentTitleBlock stamp) {
        Map<DocumentRelativeFieldAreas, Object> relativeFieldAreasWithData = new HashMap<>();
        if (stamp.getCompany() != null) {
            if (stamp.getCompany().getCity() != null && stamp.getReleaseDate() != null) {
                relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverBottom, stamp.getCompany().getCity() + " " + stamp.getReleaseDate());
            }
            relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverCompanyName, stamp.getCompany().getName().toUpperCase(Locale.ROOT));
        }
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverProjectName, stamp.getProjectName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverVolumeName, stamp.getVolumeName());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverStage, stamp.getStage().toUpperCase(Locale.ROOT));
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverVolumeNumber, "Том " + stamp.getVolumeNumber());
        relativeFieldAreasWithData.put(DocumentRelativeFieldAreas.CoverVolumeCode, stamp.getDocumentCode());
        return relativeFieldAreasWithData;
    }

    private Set<DocumentRelativeFieldAreas> getSignAreas() {
        Set<DocumentRelativeFieldAreas> fieldAreas = new HashSet<>();
        fieldAreas.add(DocumentRelativeFieldAreas.SmallSupervisorSign);
        fieldAreas.add(DocumentRelativeFieldAreas.SmallDesignerSign);
        fieldAreas.add(DocumentRelativeFieldAreas.SupervisorSign);
        fieldAreas.add(DocumentRelativeFieldAreas.DesignerSign);
        fieldAreas.add(DocumentRelativeFieldAreas.ChiefEngineerSign);
        fieldAreas.add(DocumentRelativeFieldAreas.ControllerSign);
        return fieldAreas;
    }

    private Paragraph getParagraph(Rectangle paragraphArea, Integer correctionInMM) {
        return new Paragraph()
                .setFixedPosition(paragraphArea.getLeft() - millimetersToPoints(correctionInMM)
                        , paragraphArea.getBottom() - 2 * millimetersToPoints(correctionInMM)
                        , paragraphArea.getWidth() + 2 * millimetersToPoints(correctionInMM))
                .setHeight(paragraphArea.getHeight() + 4 * millimetersToPoints(correctionInMM))
                .setMargin(0)
                .setPadding(0)
                .setMultipliedLeading(0.8f)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
    }

    private List<Paragraph> getTitleBlockFilledFields(
            Object content,
            DocumentRelativeFieldAreas fieldArea,
            PdfDocument pdfDocument) {
        List<Paragraph> filledFields = new ArrayList<>();
        if (content != null) {
            String textForParagraph = "";
            Image image;
            Map<Rectangle, Integer> fieldAreasOnPageInTitleBlockOfDocument = getFieldAreasInTitleBlockOnPageOfDocument(pdfDocument, fieldArea);
            for (Map.Entry<Rectangle, Integer> areaOnPage : fieldAreasOnPageInTitleBlockOfDocument.entrySet()) {
                if (getSignAreas().contains(fieldArea)) {
                    Paragraph paragraphForSign = getParagraph(areaOnPage.getKey(), 4);
                    paragraphForSign.setPageNumber(areaOnPage.getValue())
                            .setTextAlignment(TextAlignment.CENTER);
                    if (!(content instanceof byte[] signInBytes)) throw new AssertionError();
                    image = new Image(ImageDataFactory.create(signInBytes))
                            .setAutoScale(true);
                    paragraphForSign.add(image);
                    filledFields.add(paragraphForSign);
                    continue;
                }
                Paragraph paragraph = getParagraph(areaOnPage.getKey(), 0);
                paragraph.setTextAlignment(fieldArea.getAlignment())
                        .setPageNumber(areaOnPage.getValue());
                if (fieldArea.equals(DocumentRelativeFieldAreas.CompanyName)) {
                    if (!(content instanceof DocumentCompany company)) throw new AssertionError();
                    if (company.getLogo() != null) {
                        image = new Image(ImageDataFactory.create(company.getLogo()))
                                .setAutoScale(true)
                                .setHorizontalAlignment(HorizontalAlignment.CENTER);
                        paragraph.add(image);
                        filledFields.add(paragraph);
                        continue;
                    } else if (company.getName() != null) textForParagraph = company.getName();
                }
                if (content instanceof String string) textForParagraph = string;

                if (fieldArea.equals(DocumentRelativeFieldAreas.AdditionalCode)) {
                    paragraph.setRotationAngle(180 * Math.PI / 180);
                }
                if (fieldArea.equals(DocumentRelativeFieldAreas.PageNumberInFirstPage)
                        || fieldArea.equals(DocumentRelativeFieldAreas.SmallPageNumberInFirstPage)
                        || fieldArea.equals(DocumentRelativeFieldAreas.PageNumberInSecondPage)) {
                    if (pdfDocument.getNumberOfPages() > 1) {
                        textForParagraph = String.valueOf(areaOnPage.getValue());
                    }
                }
                if (fieldArea.equals(DocumentRelativeFieldAreas.TotalPagesCountInFirstPage)
                        || fieldArea.equals(DocumentRelativeFieldAreas.SmallTotalPagesCountInFirstPage)) {
                    textForParagraph = String.valueOf(pdfDocument.getNumberOfPages());
                }
                Text text = new Text(textForParagraph)
                        .setFont(mainFont)
                        .setFontSize(fieldArea.getFontSize())
                        .setHorizontalScaling(1f)
                        .setItalic()
                        .setTextRise(millimetersToPoints(-0.3f));
                paragraph.add(text);
                filledFields.add(paragraph);
            }
        }
        return filledFields;
    }

    public byte[] getPdfDocumentInBytes() {
        return pdfDocumentInBytes;
    }

}
