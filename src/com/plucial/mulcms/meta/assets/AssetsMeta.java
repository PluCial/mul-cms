package com.plucial.mulcms.meta.assets;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-07-06 01:11:22")
/** */
public final class AssetsMeta extends org.slim3.datastore.ModelMeta<com.plucial.mulcms.model.assets.Assets> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.util.Date> createDate = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.util.Date>(this, "createDate", "createDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.UnindexedAttributeMeta<com.plucial.mulcms.model.assets.Assets, com.google.appengine.api.datastore.Text> html = new org.slim3.datastore.UnindexedAttributeMeta<com.plucial.mulcms.model.assets.Assets, com.google.appengine.api.datastore.Text>(this, "html", "html", com.google.appengine.api.datastore.Text.class);

    /** */
    public final org.slim3.datastore.CoreUnindexedAttributeMeta<com.plucial.mulcms.model.assets.Assets, com.plucial.global.Lang> htmlLang = new org.slim3.datastore.CoreUnindexedAttributeMeta<com.plucial.mulcms.model.assets.Assets, com.plucial.global.Lang>(this, "htmlLang", "htmlLang", com.plucial.global.Lang.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CollectionUnindexedAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.util.List<com.plucial.global.Lang>, com.plucial.global.Lang> langList = new org.slim3.datastore.CollectionUnindexedAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.util.List<com.plucial.global.Lang>, com.plucial.global.Lang>(this, "langList", "langList", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.util.Date> updateDate = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.util.Date>(this, "updateDate", "updateDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.assets.Assets, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final org.slim3.datastore.CreationDate slim3_createDateAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationDate slim3_updateDateAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final AssetsMeta slim3_singleton = new AssetsMeta();

    /**
     * @return the singleton
     */
    public static AssetsMeta get() {
       return slim3_singleton;
    }

    /** */
    public AssetsMeta() {
        super("Assets", com.plucial.mulcms.model.assets.Assets.class);
    }

    @Override
    public com.plucial.mulcms.model.assets.Assets entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.plucial.mulcms.model.assets.Assets model = new com.plucial.mulcms.model.assets.Assets();
        model.setCreateDate((java.util.Date) entity.getProperty("createDate"));
        model.setHtml((com.google.appengine.api.datastore.Text) entity.getProperty("html"));
        model.setHtmlLang(stringToEnum(com.plucial.global.Lang.class, (java.lang.String) entity.getProperty("htmlLang")));
        model.setKey(entity.getKey());
        model.setLangList(stringListToEnumList(com.plucial.global.Lang.class, entity.getProperty("langList")));
        model.setUpdateDate((java.util.Date) entity.getProperty("updateDate"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("createDate", m.getCreateDate());
        entity.setUnindexedProperty("html", m.getHtml());
        entity.setUnindexedProperty("htmlLang", enumToString(m.getHtmlLang()));
        entity.setUnindexedProperty("langList", enumListToStringList(m.getLangList()));
        entity.setProperty("updateDate", m.getUpdateDate());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        m.setCreateDate(slim3_createDateAttributeListener.prePut(m.getCreateDate()));
        m.setUpdateDate(slim3_updateDateAttributeListener.prePut(m.getUpdateDate()));
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        com.plucial.mulcms.model.assets.Assets m = (com.plucial.mulcms.model.assets.Assets) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCreateDate() != null){
            writer.setNextPropertyName("createDate");
            encoder0.encode(writer, m.getCreateDate());
        }
        if(m.getHtml() != null && m.getHtml().getValue() != null){
            writer.setNextPropertyName("html");
            encoder0.encode(writer, m.getHtml());
        }
        if(m.getHtmlLang() != null){
            writer.setNextPropertyName("htmlLang");
            encoder0.encode(writer, m.getHtmlLang());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLangList() != null){
            writer.setNextPropertyName("langList");
            writer.beginArray();
            for(com.plucial.global.Lang v : m.getLangList()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getUpdateDate() != null){
            writer.setNextPropertyName("updateDate");
            encoder0.encode(writer, m.getUpdateDate());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.plucial.mulcms.model.assets.Assets jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.plucial.mulcms.model.assets.Assets m = new com.plucial.mulcms.model.assets.Assets();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("createDate");
        m.setCreateDate(decoder0.decode(reader, m.getCreateDate()));
        reader = rootReader.newObjectReader("html");
        m.setHtml(decoder0.decode(reader, m.getHtml()));
        reader = rootReader.newObjectReader("htmlLang");
        m.setHtmlLang(decoder0.decode(reader, m.getHtmlLang(), com.plucial.global.Lang.class));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("langList");
        {
            java.util.ArrayList<com.plucial.global.Lang> elements = new java.util.ArrayList<com.plucial.global.Lang>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("langList");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    elements.add(decoder0.decode(reader, (com.plucial.global.Lang)null, com.plucial.global.Lang.class));
                }
                m.setLangList(elements);
            }
        }
        reader = rootReader.newObjectReader("updateDate");
        m.setUpdateDate(decoder0.decode(reader, m.getUpdateDate()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}