package com.xat.core.utils;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import javax.imageio.ImageIO;

public class ImageUtils {
   private static final String EMPTY_IMAGE_NAME = "blank.jpg";
   private static final byte[] EMPTY_IMAGE_CONTENT;

   public static byte[] resize(File input, int size) {
      byte[] output = null;

      try {
         BufferedImage bi = ImageIO.read(input);
         bi = Scalr.resize(bi, Method.ULTRA_QUALITY, size);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(bi, "jpg", baos);
         baos.flush();
         output = baos.toByteArray();
         baos.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return output;
   }

   public static byte[] crop(byte[] input, int x, int y, int with, int height) {
      byte[] output = null;
      ByteArrayInputStream bais = new ByteArrayInputStream(input);

      try {
         BufferedImage bi = ImageIO.read(bais);
         bi = Scalr.crop(bi, x, y, with, height, new BufferedImageOp[0]);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(bi, "jpg", baos);
         baos.flush();
         output = baos.toByteArray();
         baos.close();
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      return output;
   }

   public static boolean isImage(String contentType) {
      return !CommonUtils.isEmpty(contentType) && (contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpeg") || contentType.equalsIgnoreCase("image/gif"));
   }

   public static byte[] getEmptyImage() {
      return EMPTY_IMAGE_CONTENT;
   }

   static {
      String filePath = ImageUtils.class.getClassLoader().getResource("blank.jpg").getPath();
      File mediaFile = new File(filePath);
      EMPTY_IMAGE_CONTENT = FileUtils.readFile(mediaFile);
   }
}
