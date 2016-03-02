package com.example.zelong.tincanapi.Models;

/**
 * Created by zelong on 3/2/16.
 */
public class Statement {

    private Actor actor;
    private Verb verb;
    private Objet objet;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public Objet getObjet() {
        return objet;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }
}
