import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageCompressor {
    float compressionQuality;

    void compressPNG(File imageFile, String filePath, String outputDirectory) throws Exception {

        BufferedImage image = ImageIO.read(imageFile);

        File compressedImage = new File(outputDirectory +"\\" +imageFile.getName().replaceFirst("[.][^.]+$", "")+"-compressed.png");
        OutputStream os = new FileOutputStream(compressedImage);

        ImageWriteParam param = ImageIO. getImageWritersByFormatName("png").next().getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(compressionQuality);

        ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName("png").next();
        writer.setOutput(ImageIO.createImageOutputStream(os));
        writer.write(null, new IIOImage(image, null,null), param);


        os.close();
        writer.dispose();
    }
}
