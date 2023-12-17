package com.xat.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class FileUtils extends org.apache.commons.io.FileUtils {
   public static byte[] readFile(File file) {
      if (!CommonUtils.isNull(file) && file.exists()) {
         byte[] content = null;

         try {
            FileInputStream is = new FileInputStream(file);

            try {
               content = IOUtils.toByteArray(is);
            } catch (Throwable var6) {
               try {
                  is.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }

               throw var6;
            }

            is.close();
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         return content;
      } else {
         return null;
      }
   }

   public static void saveFile(String filePath, byte[] data) {
      try {
         FileOutputStream stream = new FileOutputStream(filePath);
         stream.write(data);
         stream.close();
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public static String getFileExtension(String filename) {
      if (CommonUtils.isEmpty(filename)) {
         return null;
      } else {
         int pos = filename.lastIndexOf(".");
         return pos > 0 && pos < filename.length() ? filename.substring(pos + 1) : null;
      }
   }

   public static String getContentType(File file) {
      String contentType = "text/html";
      if (!CommonUtils.isNull(file) && file.exists()) {
         try {
            FileInputStream is = new FileInputStream(file);

            try {
               ContentHandler contenthandler = new BodyContentHandler();
               Metadata metadata = new Metadata();
               metadata.set("resourceName", file.getName());
               Parser parser = new AutoDetectParser();
               parser.parse(is, contenthandler, metadata, new ParseContext());
               contentType = metadata.get("Content-Type");
            } catch (Throwable var7) {
               try {
                  is.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }

               throw var7;
            }

            is.close();
         } catch (Exception var8) {
            var8.printStackTrace();
         }

         return contentType;
      } else {
         return contentType;
      }
   }

   public static String getFontAwesomeIcon(String fileExt) {
      String icon = "fa-file-o";
      if (CommonUtils.isEmpty(fileExt)) {
         return icon;
      } else {
         String var2 = fileExt.toLowerCase();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -899798672:
            if (var2.equals("sldasm")) {
               var3 = 76;
            }
            break;
         case -899784281:
            if (var2.equals("sldprt")) {
               var3 = 77;
            }
            break;
         case 99:
            if (var2.equals("c")) {
               var3 = 47;
            }
            break;
         case 104:
            if (var2.equals("h")) {
               var3 = 49;
            }
            break;
         case 3112:
            if (var2.equals("ai")) {
               var3 = 67;
            }
            break;
         case 3124:
            if (var2.equals("au")) {
               var3 = 87;
            }
            break;
         case 3315:
            if (var2.equals("gz")) {
               var3 = 42;
            }
            break;
         case 3587:
            if (var2.equals("ps")) {
               var3 = 38;
            }
            break;
         case 52226:
            if (var2.equals("3ds")) {
               var3 = 73;
            }
            break;
         case 52254:
            if (var2.equals("3g2")) {
               var3 = 126;
            }
            break;
         case 52316:
            if (var2.equals("3gp")) {
               var3 = 125;
            }
            break;
         case 96323:
            if (var2.equals("aac")) {
               var3 = 84;
            }
            break;
         case 96402:
            if (var2.equals("act")) {
               var3 = 82;
            }
            break;
         case 96710:
            if (var2.equals("amr")) {
               var3 = 85;
            }
            break;
         case 96790:
            if (var2.equals("ape")) {
               var3 = 86;
            }
            break;
         case 96980:
            if (var2.equals("avi")) {
               var3 = 112;
            }
            break;
         case 97004:
            if (var2.equals("awb")) {
               var3 = 88;
            }
            break;
         case 97669:
            if (var2.equals("bmp")) {
               var3 = 66;
            }
            break;
         case 98723:
            if (var2.equals("cpp")) {
               var3 = 48;
            }
            break;
         case 98822:
            if (var2.equals("csv")) {
               var3 = 21;
            }
            break;
         case 99285:
            if (var2.equals("dct")) {
               var3 = 89;
            }
            break;
         case 99640:
            if (var2.equals("doc")) {
               var3 = 0;
            }
            break;
         case 99657:
            if (var2.equals("dot")) {
               var3 = 2;
            }
            break;
         case 99780:
            if (var2.equals("dss")) {
               var3 = 90;
            }
            break;
         case 99781:
            if (var2.equals("dst")) {
               var3 = 72;
            }
            break;
         case 99860:
            if (var2.equals("dvf")) {
               var3 = 91;
            }
            break;
         case 99891:
            if (var2.equals("dwf")) {
               var3 = 71;
            }
            break;
         case 99892:
            if (var2.equals("dwg")) {
               var3 = 68;
            }
            break;
         case 99905:
            if (var2.equals("dwt")) {
               var3 = 69;
            }
            break;
         case 99922:
            if (var2.equals("dxf")) {
               var3 = 70;
            }
            break;
         case 100542:
            if (var2.equals("emf")) {
               var3 = 57;
            }
            break;
         case 100648:
            if (var2.equals("eps")) {
               var3 = 39;
            }
            break;
         case 101488:
            if (var2.equals("flv")) {
               var3 = 108;
            }
            break;
         case 102340:
            if (var2.equals("gif")) {
               var3 = 53;
            }
            break;
         case 102657:
            if (var2.equals("gsm")) {
               var3 = 93;
            }
            break;
         case 103515:
            if (var2.equals("j2c")) {
               var3 = 63;
            }
            break;
         case 103523:
            if (var2.equals("j2k")) {
               var3 = 62;
            }
            break;
         case 104678:
            if (var2.equals("ivs")) {
               var3 = 95;
            }
            break;
         case 105388:
            if (var2.equals("jp2")) {
               var3 = 61;
            }
            break;
         case 105437:
            if (var2.equals("jpc")) {
               var3 = 64;
            }
            break;
         case 105439:
            if (var2.equals("jpe")) {
               var3 = 58;
            }
            break;
         case 105440:
            if (var2.equals("jpf")) {
               var3 = 59;
            }
            break;
         case 105441:
            if (var2.equals("jpg")) {
               var3 = 51;
            }
            break;
         case 105458:
            if (var2.equals("jpx")) {
               var3 = 60;
            }
            break;
         case 106417:
            if (var2.equals("m2v")) {
               var3 = 124;
            }
            break;
         case 106458:
            if (var2.equals("m4a")) {
               var3 = 96;
            }
            break;
         case 106473:
            if (var2.equals("m4p")) {
               var3 = 117;
            }
            break;
         case 106479:
            if (var2.equals("m4v")) {
               var3 = 118;
            }
            break;
         case 108184:
            if (var2.equals("mkv")) {
               var3 = 107;
            }
            break;
         case 108230:
            if (var2.equals("mmf")) {
               var3 = 97;
            }
            break;
         case 108262:
            if (var2.equals("mng")) {
               var3 = 111;
            }
            break;
         case 108271:
            if (var2.equals("mp2")) {
               var3 = 120;
            }
            break;
         case 108272:
            if (var2.equals("mp3")) {
               var3 = 98;
            }
            break;
         case 108273:
            if (var2.equals("mp4")) {
               var3 = 116;
            }
            break;
         case 108308:
            if (var2.equals("mov")) {
               var3 = 113;
            }
            break;
         case 108320:
            if (var2.equals("mpc")) {
               var3 = 99;
            }
            break;
         case 108322:
            if (var2.equals("mpe")) {
               var3 = 122;
            }
            break;
         case 108324:
            if (var2.equals("mpg")) {
               var3 = 119;
            }
            break;
         case 108339:
            if (var2.equals("mpv")) {
               var3 = 123;
            }
            break;
         case 108432:
            if (var2.equals("msv")) {
               var3 = 100;
            }
            break;
         case 108958:
            if (var2.equals("neu")) {
               var3 = 81;
            }
            break;
         case 109961:
            if (var2.equals("oga")) {
               var3 = 102;
            }
            break;
         case 109967:
            if (var2.equals("ogg")) {
               var3 = 101;
            }
            break;
         case 109982:
            if (var2.equals("ogv")) {
               var3 = 110;
            }
            break;
         case 110821:
            if (var2.equals("pcx")) {
               var3 = 56;
            }
            break;
         case 110834:
            if (var2.equals("pdf")) {
               var3 = 37;
            }
            break;
         case 111145:
            if (var2.equals("png")) {
               var3 = 50;
            }
            break;
         case 111189:
            if (var2.equals("pot")) {
               var3 = 29;
            }
            break;
         case 111219:
            if (var2.equals("pps")) {
               var3 = 28;
            }
            break;
         case 111220:
            if (var2.equals("ppt")) {
               var3 = 26;
            }
            break;
         case 111282:
            if (var2.equals("prt")) {
               var3 = 78;
            }
            break;
         case 111297:
            if (var2.equals("psd")) {
               var3 = 65;
            }
            break;
         case 111316:
            if (var2.equals("psw")) {
               var3 = 13;
            }
            break;
         case 111460:
            if (var2.equals("pxl")) {
               var3 = 24;
            }
            break;
         case 112675:
            if (var2.equals("rar")) {
               var3 = 41;
            }
            break;
         case 113252:
            if (var2.equals("rtf")) {
               var3 = 45;
            }
            break;
         case 113712:
            if (var2.equals("sda")) {
               var3 = 33;
            }
            break;
         case 113714:
            if (var2.equals("sdc")) {
               var3 = 22;
            }
            break;
         case 113715:
            if (var2.equals("sdd")) {
               var3 = 34;
            }
            break;
         case 113727:
            if (var2.equals("sdp")) {
               var3 = 35;
            }
            break;
         case 113734:
            if (var2.equals("sdw")) {
               var3 = 8;
            }
            break;
         case 113816:
            if (var2.equals("sgl")) {
               var3 = 9;
            }
            break;
         case 114597:
            if (var2.equals("tar")) {
               var3 = 43;
            }
            break;
         case 114833:
            if (var2.equals("tif")) {
               var3 = 54;
            }
            break;
         case 115312:
            if (var2.equals("txt")) {
               var3 = 44;
            }
            break;
         case 115980:
            if (var2.equals("uof")) {
               var3 = 12;
            }
            break;
         case 115990:
            if (var2.equals("uop")) {
               var3 = 36;
            }
            break;
         case 115993:
            if (var2.equals("uos")) {
               var3 = 23;
            }
            break;
         case 115994:
            if (var2.equals("uot")) {
               var3 = 11;
            }
            break;
         case 116937:
            if (var2.equals("vob")) {
               var3 = 109;
            }
            break;
         case 116953:
            if (var2.equals("vor")) {
               var3 = 10;
            }
            break;
         case 116959:
            if (var2.equals("vox")) {
               var3 = 104;
            }
            break;
         case 117447:
            if (var2.equals("wb2")) {
               var3 = 25;
            }
            break;
         case 117484:
            if (var2.equals("wav")) {
               var3 = 105;
            }
            break;
         case 117835:
            if (var2.equals("wma")) {
               var3 = 106;
            }
            break;
         case 117856:
            if (var2.equals("wmv")) {
               var3 = 114;
            }
            break;
         case 117931:
            if (var2.equals("wpd")) {
               var3 = 6;
            }
            break;
         case 117946:
            if (var2.equals("wps")) {
               var3 = 7;
            }
            break;
         case 118001:
            if (var2.equals("wrl")) {
               var3 = 74;
            }
            break;
         case 118442:
            if (var2.equals("xas")) {
               var3 = 80;
            }
            break;
         case 118783:
            if (var2.equals("xls")) {
               var3 = 14;
            }
            break;
         case 118784:
            if (var2.equals("xlt")) {
               var3 = 17;
            }
            break;
         case 118787:
            if (var2.equals("xlw")) {
               var3 = 16;
            }
            break;
         case 118906:
            if (var2.equals("xpr")) {
               var3 = 79;
            }
            break;
         case 120609:
            if (var2.equals("zip")) {
               var3 = 40;
            }
            break;
         case 2993896:
            if (var2.equals("aiff")) {
               var3 = 83;
            }
            break;
         case 3088949:
            if (var2.equals("docm")) {
               var3 = 3;
            }
            break;
         case 3088960:
            if (var2.equals("docx")) {
               var3 = 1;
            }
            break;
         case 3089476:
            if (var2.equals("dotm")) {
               var3 = 5;
            }
            break;
         case 3089487:
            if (var2.equals("dotx")) {
               var3 = 4;
            }
            break;
         case 3145576:
            if (var2.equals("flac")) {
               var3 = 92;
            }
            break;
         case 3254818:
            if (var2.equals("java")) {
               var3 = 46;
            }
            break;
         case 3268712:
            if (var2.equals("jpeg")) {
               var3 = 52;
            }
            break;
         case 3358085:
            if (var2.equals("mpeg")) {
               var3 = 121;
            }
            break;
         case 3418175:
            if (var2.equals("opus")) {
               var3 = 103;
            }
            break;
         case 3446968:
            if (var2.equals("potm")) {
               var3 = 31;
            }
            break;
         case 3446979:
            if (var2.equals("potx")) {
               var3 = 32;
            }
            break;
         case 3447929:
            if (var2.equals("pptm")) {
               var3 = 30;
            }
            break;
         case 3447940:
            if (var2.equals("pptx")) {
               var3 = 27;
            }
            break;
         case 3504679:
            if (var2.equals("rmvb")) {
               var3 = 115;
            }
            break;
         case 3559925:
            if (var2.equals("tiff")) {
               var3 = 55;
            }
            break;
         case 3628379:
            if (var2.equals("vrml")) {
               var3 = 75;
            }
            break;
         case 3682382:
            if (var2.equals("xlsm")) {
               var3 = 18;
            }
            break;
         case 3682393:
            if (var2.equals("xlsx")) {
               var3 = 15;
            }
            break;
         case 3682413:
            if (var2.equals("xltm")) {
               var3 = 20;
            }
            break;
         case 3682424:
            if (var2.equals("xltx")) {
               var3 = 19;
            }
            break;
         case 100264257:
            if (var2.equals("iklax")) {
               var3 = 94;
            }
         }

         switch(var3) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
            icon = "fa-file-word-o";
            break;
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
            icon = "fa-file-excel-o";
            break;
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
            icon = "fa-file-powerpoint-o";
            break;
         case 37:
         case 38:
         case 39:
            icon = "fa-file-pdf-o";
            break;
         case 40:
         case 41:
         case 42:
         case 43:
            icon = "fa-file-zip-o";
            break;
         case 44:
         case 45:
            icon = "fa-file-text-o";
            break;
         case 46:
         case 47:
         case 48:
         case 49:
            icon = "fa-file-code-o";
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
            icon = "fa-file-image-o";
            break;
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
            icon = "fa-file-audio-o";
            break;
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
            icon = "fa-file-video-o";
            break;
         default:
            icon = "fa-file-o";
         }

         return icon;
      }
   }

   public static String appendPath(String path1, String path2, boolean reverse) {
      if (CommonUtils.isEmpty(path1) && CommonUtils.isEmpty(path2)) {
         return "";
      } else if (CommonUtils.isEmpty(path1)) {
         return path2;
      } else if (CommonUtils.isEmpty(path2)) {
         return path1;
      } else if (reverse) {
         if (path2.endsWith(File.pathSeparator)) {
            path2 = path2.substring(0, path2.length() - 1);
         }

         if (path1.startsWith(File.pathSeparator)) {
            path1 = path1.substring(1);
         }

         return path2 + File.separator + path1;
      } else {
         if (path1.endsWith(File.pathSeparator)) {
            path1 = path1.substring(0, path1.length() - 1);
         }

         if (path2.startsWith(File.pathSeparator)) {
            path2 = path2.substring(1);
         }

         return path1 + File.separator + path2;
      }
   }

   public static File createUniqueFolder(String parentPath) {
      File folder = null;

      while(folder == null) {
         folder = new File(appendPath(parentPath, String.valueOf(System.nanoTime()), false));
         if (folder.exists()) {
            folder = null;
         } else {
            folder.mkdir();
         }
      }

      return folder;
   }
}
