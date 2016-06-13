package com.plucial.mulcms.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.RenderingMeta;
import com.plucial.mulcms.model.Rendering;
import com.plucial.mulcms.model.assets.Assets;

public class RenderingDao extends DaoBase<Rendering>{

    /** META */
    private static final RenderingMeta meta = RenderingMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Rendering> getList(Assets assets) {
        return  Datastore.query(meta).filter(
            meta.assetsRef.equal(assets.getKey())
                ).sort(new Sort(meta.createDate)).asList();
    }
}
