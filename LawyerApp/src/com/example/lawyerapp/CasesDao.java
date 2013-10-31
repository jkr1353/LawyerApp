package com.example.lawyerapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.lawyerapp.Cases;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CASES.
*/
public class CasesDao extends AbstractDao<Cases, Long> {

    public static final String TABLENAME = "CASES";

    /**
     * Properties of entity Cases.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Casetype = new Property(2, String.class, "casetype", false, "CASETYPE");
        public final static Property CaseDate = new Property(3, String.class, "caseDate", false, "CASE_DATE");
        public final static Property Date = new Property(4, java.util.Date.class, "date", false, "DATE");
    };


    public CasesDao(DaoConfig config) {
        super(config);
    }
    
    public CasesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CASES' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'CASETYPE' TEXT," + // 2: casetype
                "'CASE_DATE' TEXT," + // 3: caseDate
                "'DATE' INTEGER);"); // 4: date
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CASES'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Cases entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String casetype = entity.getCasetype();
        if (casetype != null) {
            stmt.bindString(3, casetype);
        }
 
        String caseDate = entity.getCaseDate();
        if (caseDate != null) {
            stmt.bindString(4, caseDate);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(5, date.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Cases readEntity(Cursor cursor, int offset) {
        Cases entity = new Cases( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // casetype
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // caseDate
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)) // date
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Cases entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCasetype(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCaseDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDate(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Cases entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Cases entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}