package com.__01.APP.Tesis.Perfiles.dto;

import java.util.List;

public class UpdateProfileRequest {
    public String bannerUrl;
    public String logoUrl;
    public String name;
    public String industry;
    public String description;
    public List<String> tags;
    public String location;
    public String founded;
    public String employees;
    public String website;
    public String whatsapp;
    public List<SocialLinkDto> socialLinks;
}