package org.collectionspace.services.authentication;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public Passwordreset createPasswordreset() {
        return new Passwordreset();
    }

    public User createUser() {
        return new User();
    }

    public Token createToken() {
        return new Token();
    }

}
