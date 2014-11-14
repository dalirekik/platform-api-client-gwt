/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.ui.zeroClipboard;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

import org.vectomatic.dom.svg.ui.SVGResource;

/**
 * @author Oleksii Orel
 */
public interface ZeroClipboardResources extends ClientBundle {
    public interface Css extends CssResource {
        String clipboardButton();
    }

    @Source({"ZeroClipboard.css", "com/codenvy/ide/api/ui/style.css"})
    Css clipboardCss();

    @Source("clipboard.svg")
    SVGResource clipboard();
}