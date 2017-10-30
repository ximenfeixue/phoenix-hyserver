package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.model.meeting.DataSync;

import java.util.List;

/**
 * Created by wang fei on 2017/10/27.
 */
public interface DataSyncMongoDao {

    void saveDataSync(DataSync data);

    void batchSaveDataSync(List<DataSync> dataList);

    boolean deleteDataSync(final String id);

    List<DataSync> getDataSyncList();
}
