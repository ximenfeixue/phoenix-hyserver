package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.DataSyncMongoDao;
import com.ginkgocap.ywxt.model.meeting.DataSync;
import com.ginkgocap.ywxt.service.meeting.DataSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2017/10/27.
 */
@Service("dataSyncService")
public class DataSyncServiceImpl implements DataSyncService {

    @Autowired
    private DataSyncMongoDao dataSyncMongoDao;

    @Override
    public void saveDataSync(DataSync data) {
        dataSyncMongoDao.saveDataSync(data);
    }

    @Override
    public void batchSaveDataSync(List<DataSync> dataList) {
        dataSyncMongoDao.batchSaveDataSync(dataList);
    }

    @Override
    public boolean deleteDataSync(Object id) {
        return dataSyncMongoDao.deleteDataSync(id);
    }

    @Override
    public List<DataSync> getDataSyncList() {
        return dataSyncMongoDao.getDataSyncList();
    }
}
