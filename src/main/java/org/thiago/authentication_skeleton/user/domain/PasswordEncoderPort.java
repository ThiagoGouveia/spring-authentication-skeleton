package org.thiago.authentication_skeleton.user.domain;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
