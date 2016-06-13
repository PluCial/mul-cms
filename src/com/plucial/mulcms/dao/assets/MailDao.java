package com.plucial.mulcms.dao.assets;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.assets.MailMeta;
import com.plucial.mulcms.model.assets.Mail;

public class MailDao extends DaoBase<Mail>{
    
    /** META */
    private static final MailMeta meta = MailMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Mail> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }

}
