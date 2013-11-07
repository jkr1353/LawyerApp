package com.example.lawyerapp;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.lawyerapp.Files;
import com.example.lawyerapp.Logs;
import com.example.lawyerapp.Cases;

import com.example.lawyerapp.FilesDao;
import com.example.lawyerapp.LogsDao;
import com.example.lawyerapp.CasesDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig filesDaoConfig;
    private final DaoConfig logsDaoConfig;
    private final DaoConfig casesDaoConfig;

    private final FilesDao filesDao;
    private final LogsDao logsDao;
    private final CasesDao casesDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        filesDaoConfig = daoConfigMap.get(FilesDao.class).clone();
        filesDaoConfig.initIdentityScope(type);

        logsDaoConfig = daoConfigMap.get(LogsDao.class).clone();
        logsDaoConfig.initIdentityScope(type);

        casesDaoConfig = daoConfigMap.get(CasesDao.class).clone();
        casesDaoConfig.initIdentityScope(type);

        filesDao = new FilesDao(filesDaoConfig, this);
        logsDao = new LogsDao(logsDaoConfig, this);
        casesDao = new CasesDao(casesDaoConfig, this);

        registerDao(Files.class, filesDao);
        registerDao(Logs.class, logsDao);
        registerDao(Cases.class, casesDao);
    }
    
    public void clear() {
        filesDaoConfig.getIdentityScope().clear();
        logsDaoConfig.getIdentityScope().clear();
        casesDaoConfig.getIdentityScope().clear();
    }

    public FilesDao getFilesDao() {
        return filesDao;
    }

    public LogsDao getLogsDao() {
        return logsDao;
    }

    public CasesDao getCasesDao() {
        return casesDao;
    }

}
