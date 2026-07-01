package com.__01.APP.Tesis.Perfiles.dto;

import java.util.List;

public class ProfileResponse {
    public String id;
    public String userId;
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
    public String createdAt;
    public String updatedAt;
}