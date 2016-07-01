package com.plucial.mulcms.meta.res;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-07-06 01:11:22")
/** */
public final class InnerResMeta extends org.slim3.datastore.ModelMeta<com.plucial.mulcms.model.res.InnerRes> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.lang.Boolean> editMode = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.lang.Boolean>(this, "editMode", "editMode", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, com.plucial.global.Lang> lang = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, com.plucial.global.Lang>(this, "lang", "lang", com.plucial.global.Lang.class);

    /** */
    public final org.slim3.datastore.UnindexedAttributeMeta<com.plucial.mulcms.model.res.InnerRes, com.google.appengine.api.datastore.Text> value = new org.slim3.datastore.UnindexedAttributeMeta<com.plucial.mulcms.model.res.InnerRes, com.google.appengine.api.datastore.Text>(this, "value", "value", com.google.appengine.api.datastore.Text.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<com.plucial.mulcms.model.res.InnerRes, org.slim3.datastore.ModelRef<com.plucial.mulcms.model.assets.Assets>, com.plucial.mulcms.model.assets.Assets> assetsRef = new org.slim3.datastore.ModelRefAttributeMeta<com.plucial.mulcms.model.res.InnerRes, org.slim3.datastore.ModelRef<com.plucial.mulcms.model.assets.Assets>, com.plucial.mulcms.model.assets.Assets>(this, "assetsRef", "assetsRef", org.slim3.datastore.ModelRef.class, com.plucial.mulcms.model.assets.Assets.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.util.Date> createDate = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.util.Date>(this, "createDate", "createDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<com.plucial.mulcms.model.res.InnerRes> cssQuery = new org.slim3.datastore.StringAttributeMeta<com.plucial.mulcms.model.res.InnerRes>(this, "cssQuery", "cssQuery");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.util.Date> updateDate = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.util.Date>(this, "updateDate", "updateDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.res.InnerRes, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final org.slim3.datastore.CreationDate slim3_createDateAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationDate slim3_updateDateAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final InnerResMeta slim3_singleton = new InnerResMeta();

    /**
     * @return the singleton
     */
    public static InnerResMeta get() {
       return slim3_singleton;
    }

    /** */
    public InnerResMeta() {
        super("Rendering", com.plucial.mulcms.model.res.InnerRes.class, java.util.Arrays.asList("com.plucial.mulcms.model.res.Res", "com.plucial.mulcms.model.res.InnerRes"));
    }

    @Override
    public com.plucial.mulcms.model.res.InnerRes entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.plucial.mulcms.model.res.InnerRes model = new com.plucial.mulcms.model.res.InnerRes();
        model.setEditMode(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("editMode")));
        model.setLang(stringToEnum(com.plucial.global.Lang.class, (java.lang.String) entity.getProperty("lang")));
        model.setValue((com.google.appengine.api.datastore.Text) entity.getProperty("value"));
        if (model.getAssetsRef() == null) {
            throw new NullPointerException("The property(assetsRef) is null.");
        }
        model.getAssetsRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("assetsRef"));
        model.setCreateDate((java.util.Date) entity.getProperty("createDate"));
        model.setCssQuery((java.lang.String) entity.getProperty("cssQuery"));
        model.setKey(entity.getKey());
        model.setUpdateDate((java.util.Date) entity.getProperty("updateDate"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setUnindexedProperty("editMode", m.isEditMode());
        entity.setProperty("lang", enumToString(m.getLang()));
        entity.setUnindexedProperty("value", m.getValue());
        if (m.getAssetsRef() == null) {
            throw new NullPointerException("The property(assetsRef) must not be null.");
        }
        entity.setProperty("assetsRef", m.getAssetsRef().getKey());
        entity.setProperty("createDate", m.getCreateDate());
        entity.setProperty("cssQuery", m.getCssQuery());
        entity.setProperty("updateDate", m.getUpdateDate());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        entity.setProperty("slim3.classHierarchyList", classHierarchyList);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        if (m.getAssetsRef() == null) {
            throw new NullPointerException("The property(assetsRef) must not be null.");
        }
        m.getAssetsRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
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
        com.plucial.mulcms.model.res.InnerRes m = (com.plucial.mulcms.model.res.InnerRes) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        writer.setNextPropertyName("editMode");
        encoder0.encode(writer, m.isEditMode());
        if(m.getLang() != null){
            writer.setNextPropertyName("lang");
            encoder0.encode(writer, m.getLang());
        }
        if(m.getValue() != null && m.getValue().getValue() != null){
            writer.setNextPropertyName("value");
            encoder0.encode(writer, m.getValue());
        }
        if(m.getAssetsRef() != null && m.getAssetsRef().getKey() != null){
            writer.setNextPropertyName("assetsRef");
            encoder0.encode(writer, m.getAssetsRef(), maxDepth, currentDepth);
        }
        if(m.getCreateDate() != null){
            writer.setNextPropertyName("createDate");
            encoder0.encode(writer, m.getCreateDate());
        }
        if(m.getCssQuery() != null){
            writer.setNextPropertyName("cssQuery");
            encoder0.encode(writer, m.getCssQuery());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
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
    protected com.plucial.mulcms.model.res.InnerRes jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.plucial.mulcms.model.res.InnerRes m = new com.plucial.mulcms.model.res.InnerRes();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("editMode");
        m.setEditMode(decoder0.decode(reader, m.isEditMode()));
        reader = rootReader.newObjectReader("lang");
        m.setLang(decoder0.decode(reader, m.getLang(), com.plucial.global.Lang.class));
        reader = rootReader.newObjectReader("value");
        m.setValue(decoder0.decode(reader, m.getValue()));
        reader = rootReader.newObjectReader("assetsRef");
        decoder0.decode(reader, m.getAssetsRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("createDate");
        m.setCreateDate(decoder0.decode(reader, m.getCreateDate()));
        reader = rootReader.newObjectReader("cssQuery");
        m.setCssQuery(decoder0.decode(reader, m.getCssQuery()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("updateDate");
        m.setUpdateDate(decoder0.decode(reader, m.getUpdateDate()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}