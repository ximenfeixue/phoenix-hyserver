package com.ginkgocap.ywxt.service.meeting;

import com.ginkgocap.ywxt.model.meeting.DataSync;

import java.util.List;

/**
 * Created by wang fei on 2017/10/27.
 */
public interface DataSyncService {

    void saveDataSync(DataSync data);

    void batchSaveDataSync(List<DataSync> dataList);

    boolean deleteDataSync(final Object id);

    List<DataSync> getDataSyncList();
}
