package com.site.cda_demo.security;

public interface ISecurityUtils {
  String getRole(AppUserDetails userDetails);

  String generateToken(AppUserDetails userDetails);

  String getSubjectFromJwt(String jwt);
}
