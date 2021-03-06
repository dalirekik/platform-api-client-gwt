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
package com.codenvy.api.vfs.gwt.client;

import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.api.vfs.shared.dto.Item;
import com.codenvy.api.vfs.shared.dto.ReplacementSet;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.google.gwt.http.client.RequestBuilder;
import com.google.inject.name.Named;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENT_TYPE;

/**
 * Implementation for {@link com.codenvy.api.vfs.gwt.client.VfsServiceClient}.
 *
 * @author Sergii Leschenko
 * @author Artem Zatsarynnyy
 */
public class VfsServiceClientImpl implements VfsServiceClient {
    private final String VFS;
    private final String FIND_REPLACE;
    private final String GET_ITEM_BY_PATH;

    private final AsyncRequestLoader  loader;
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    public VfsServiceClientImpl(@Named("restContext") String restContext,
                                @Named("workspaceId") String workspaceId,
                                AsyncRequestLoader loader,
                                AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;

        VFS = restContext + "/vfs/" + workspaceId + "/v2";
        FIND_REPLACE = VFS + "/replace";
        GET_ITEM_BY_PATH = VFS + "/itembypath";
    }

    @Override
    public void replaceInCurrentWorkspace(@Nonnull ProjectDescriptor project,
                                          Array<ReplacementSet> replacementSets,
                                          AsyncRequestCallback<Void> callback) {
        String path = FIND_REPLACE + normalizePath(project.getPath());

        asyncRequestFactory.createRequest(RequestBuilder.POST, path, replacementSets, false)
                           .header(CONTENT_TYPE, APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void getItemByPath(@Nonnull String path, AsyncRequestCallback<Item> callback) {
        final String url = GET_ITEM_BY_PATH + normalizePath(path);

        asyncRequestFactory.createGetRequest(url)
                           .header(ACCEPT, APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    /**
     * Normalizes the path by adding a leading '/' if it doesn't exist.
     *
     * @param path
     *         path to normalize
     * @return normalized path
     */
    private String normalizePath(String path) {
        return path.startsWith("/") ? path : '/' + path;
    }
}
