package com.eastapps.mgs.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class SampleMeme {

    @ManyToOne
    private Meme sampleMeme;

    @ManyToOne
    private MemeBackground background;

	public Meme getSampleMeme() {
        return this.sampleMeme;
    }

	public void setSampleMeme(Meme sampleMeme) {
        this.sampleMeme = sampleMeme;
    }

	public MemeBackground getBackground() {
        return this.background;
    }

	public void setBackground(MemeBackground background) {
        this.background = background;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new SampleMeme().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countSampleMemes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SampleMeme o", Long.class).getSingleResult();
    }

	public static List<SampleMeme> findAllSampleMemes() {
        return entityManager().createQuery("SELECT o FROM SampleMeme o", SampleMeme.class).getResultList();
    }

	public static SampleMeme findSampleMeme(Long id) {
        if (id == null) return null;
        return entityManager().find(SampleMeme.class, id);
    }

	public static List<SampleMeme> findSampleMemeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SampleMeme o", SampleMeme.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
	
	public static List<SampleMeme> findSampleMemeEntriesForMemeBackgroundId(
		final long memeBackgroundId, 
		final int firstResult, 
		final int maxResults) 
	{
		return entityManager()
			.createQuery("SELECT o FROM SampleMeme o where o.background.id = ?", SampleMeme.class)
			.setParameter(1, memeBackgroundId)
			.setFirstResult(firstResult)
			.setMaxResults(maxResults)
			.getResultList();
	}

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SampleMeme attached = SampleMeme.findSampleMeme(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public SampleMeme merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SampleMeme merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
