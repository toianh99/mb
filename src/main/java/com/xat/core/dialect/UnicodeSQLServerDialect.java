package com.xat.core.dialect;

import org.hibernate.dialect.SQLServer2008Dialect;
import org.hibernate.type.StandardBasicTypes;

public class UnicodeSQLServerDialect extends SQLServer2008Dialect {
   public UnicodeSQLServerDialect() {
      this.registerColumnType(1, "nchar(1)");
      this.registerColumnType(-1, "nvarchar(max)");
      this.registerColumnType(12, 4000L, "nvarchar($l)");
      this.registerColumnType(12, "nvarchar(max)");
      this.registerColumnType(2005, "nvarchar(max)");
      this.registerColumnType(-15, "nchar(1)");
      this.registerColumnType(-16, "nvarchar(max)");
      this.registerColumnType(-9, 4000L, "nvarchar($l)");
      this.registerColumnType(-9, "nvarchar(max)");
      this.registerColumnType(2011, "nvarchar(max)");
      this.registerHibernateType(-15, StandardBasicTypes.CHARACTER.getName());
      this.registerHibernateType(-16, StandardBasicTypes.TEXT.getName());
      this.registerHibernateType(-9, StandardBasicTypes.STRING.getName());
      this.registerHibernateType(2011, StandardBasicTypes.CLOB.getName());
   }
}
