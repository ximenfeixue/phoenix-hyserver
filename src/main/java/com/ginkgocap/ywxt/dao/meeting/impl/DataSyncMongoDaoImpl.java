package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.DataSyncMongoDao;
import com.ginkgocap.ywxt.model.meeting.DataSync;
import com.ginkgocap.ywxt.utils.MeetingFilePrimaryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wang fei on 2017/10/27.
 */
@Repository("dataSyncMongoDao")
public class DataSyncMongoDaoImpl implements DataSyncMongoDao {

    private final Logger logger = LoggerFactory.getLogger(DataSyncMongoDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    private final int maxSize = 50;

    @Override
    public void saveDataSync(DataSync data) {

        try {
            String id = MeetingFilePrimaryKey.getPrimaryKey();
            data.setId(id);
            mongoTemplate.save(data, "dataSync");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchSaveDataSync(List<DataSync> dataList) {

        mongoTemplate.save(dataList, "dataSync");
    }

    @Override
    public boolean deleteDataSync(String id) {

        DataSync dataSync = null;
        Query query = new Query(Criteria.where("_id").is(id));
        dataSync = mongoTemplate.findAndRemove(query, DataSync.class, "dataSync");
        return dataSync != null;
    }

    @Override
    public List<DataSync> getDataSyncList() {

        Query query = new Query();
        query.skip(0);
        query.limit(maxSize);
        return mongoTemplate.find(query, DataSync.class,"dataSync");
    }
}
