/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package com.intellij.struts.tree;

import com.intellij.javaee.model.xml.web.WebApp;
import com.intellij.openapi.project.Project;
import com.intellij.struts.StrutsModel;
import com.intellij.struts.StrutsProjectComponent;
import com.intellij.struts.dom.Exception;
import com.intellij.struts.dom.*;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericDomValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Setup DOM-Tree for Struts config files.
 *
 * @author Dmitry Avdeev
 */
public class StrutsDomTree extends StrutsTreeBase<StrutsConfig, StrutsModel> {

  private final static Map<Class, Boolean> hiders = new HashMap<>();

  private final static List<Class> consolidated = Arrays.asList(
    new Class[]{Action.class, FormBean.class, Forward.class, Exception.class, SetProperty.class, FormProperty.class, DataSource.class}
  );

  private final static List<Class> folders = Arrays.asList(
    new Class[]{ActionMappings.class, FormBeans.class, GlobalForwards.class, GlobalExceptions.class, DataSources.class}
  );


  static {
    hiders.put(DomElement.class, true);
    hiders.put(GenericDomValue.class, false);
    hiders.put(Icon.class, false);
  }

  public StrutsDomTree(final Project project) {
    super(project,
          StrutsProjectComponent.getInstance(project).getStrutsFactory(),
          hiders, consolidated, folders, Arrays.asList(StrutsConfig.class, WebApp.class));
  }

}
