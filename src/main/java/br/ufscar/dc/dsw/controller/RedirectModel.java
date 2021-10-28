package br.ufscar.dc.dsw.controller;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class RedirectModel {
    @Pattern(regexp="^/([^/].*)?$")
    @NotBlank
    private String continueUrl;

    public void setContinue(String continueUrl) {
        this.continueUrl = continueUrl;
    }

    public String getContinue() {
        return continueUrl;
    }
}
