/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.ui.loader;

import com.codenvy.ide.rest.AsyncRequestLoader;

/**
 * @author Vitaliy Gulyy
 */
public class EmptyLoader implements AsyncRequestLoader {

    @Override
    public void hide() {
    }

    @Override
    public void hide(String message) {

    }

    @Override
    public void show() {
    }

    @Override
    public void show(String message) {

    }
}