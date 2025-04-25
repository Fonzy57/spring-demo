package com.site.cda_demo.mock;

import com.site.cda_demo.security.AppUserDetails;
import com.site.cda_demo.security.ISecurityUtils;

public class MockSecurityUtils implements ISecurityUtils {

  private String role;

  public MockSecurityUtils(String role) {
    this.role = role;
  }

  @Override
  public String getRole(AppUserDetails userDetails) {
    return role;
  }

  @Override
  public String generateToken(AppUserDetails userDetails) {
    return "";
  }

  @Override
  public String getSubjectFromJwt(String jwt) {
    return "";
  }
}
