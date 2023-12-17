package com.xat.core.utils;

import java.io.IOException;
import java.io.InputStream;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class HtmlUtils implements InitializingBean {
   @Autowired
   private ResourceLoader resourceLoader;
   private AntiSamy sanitizer = null;

   public void afterPropertiesSet() throws Exception {
      try {
         InputStream is = this.resourceLoader.getResource("classpath:antisamy.xml").getInputStream();

         try {
            Policy policy = Policy.getInstance(is);
            this.sanitizer = new AntiSamy(policy);
         } catch (Throwable var5) {
            if (is != null) {
               try {
                  is.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (is != null) {
            is.close();
         }
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   public String removeXSSThreats(String input) {
      if (CommonUtils.isEmpty(input)) {
         return "";
      } else {
         CleanResults scanned = null;

         try {
            scanned = this.sanitizer.scan(input);
         } catch (PolicyException var4) {
            var4.printStackTrace();
         } catch (ScanException var5) {
            var5.printStackTrace();
         }

         return CommonUtils.isNull(scanned) ? input : scanned.getCleanHTML();
      }
   }
}
