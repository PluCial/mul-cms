package com.plucial.mulcms.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-07-06 01:11:22")
/** */
public final class AppMeta extends org.slim3.datastore.ModelMeta<com.plucial.mulcms.model.App> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.App, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.App, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.UnindexedAttributeMeta<com.plucial.mulcms.model.App, com.google.appengine.api.datastore.Text> value = new org.slim3.datastore.UnindexedAttributeMeta<com.plucial.mulcms.model.App, com.google.appengine.api.datastore.Text>(this, "value", "value", com.google.appengine.api.datastore.Text.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.App, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.App, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final AppMeta slim3_singleton = new AppMeta();

    /**
     * @return the singleton
     */
    public static AppMeta get() {
       return slim3_singleton;
    }

    /** */
    public AppMeta() {
        super("App", com.plucial.mulcms.model.App.class);
    }

    @Override
    public com.plucial.mulcms.model.App entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.plucial.mulcms.model.App model = new com.plucial.mulcms.model.App();
        model.setKey(entity.getKey());
        model.setValue((com.google.appengine.api.datastore.Text) entity.getProperty("value"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.plucial.mulcms.model.App m = (com.plucial.mulcms.model.App) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setUnindexedProperty("value", m.getValue());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.plucial.mulcms.model.App m = (com.plucial.mulcms.model.App) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.plucial.mulcms.model.App m = (com.plucial.mulcms.model.App) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.plucial.mulcms.model.App m = (com.plucial.mulcms.model.App) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        com.plucial.mulcms.model.App m = (com.plucial.mulcms.model.App) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
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
        com.plucial.mulcms.model.App m = (com.plucial.mulcms.model.App) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getValue() != null && m.getValue().getValue() != null){
            writer.setNextPropertyName("value");
            encoder0.encode(writer, m.getValue());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.plucial.mulcms.model.App jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.plucial.mulcms.model.App m = new com.plucial.mulcms.model.App();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("value");
        m.setValue(decoder0.decode(reader, m.getValue()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}