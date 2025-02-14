/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.admin.service.publish;

import org.apache.shenyu.admin.listener.DataChangedEvent;
import org.apache.shenyu.admin.model.entity.SelectorDO;
import org.apache.shenyu.admin.model.enums.EventTypeEnum;
import org.apache.shenyu.admin.model.event.AdminDataModelChangedEvent;
import org.apache.shenyu.admin.model.event.selector.BatchSelectorDeletedEvent;
import org.apache.shenyu.admin.model.event.selector.SelectorChangedEvent;
import org.apache.shenyu.admin.model.event.selector.SelectorCreatedEvent;
import org.apache.shenyu.admin.utils.SessionUtil;
import org.apache.shenyu.common.dto.SelectorData;
import org.apache.shenyu.common.enums.ConfigGroupEnum;
import org.apache.shenyu.common.enums.DataEventTypeEnum;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * SelectorEventPublisher.
 */
@Component
public class SelectorEventPublisher implements AdminDataModelChangedEventPublisher<SelectorDO> {
    
    private final ApplicationEventPublisher publisher;
    
    public SelectorEventPublisher(final ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    
    /**
     * on selector created.
     *
     * @param selector selector
     */
    @Override
    public void onCreated(final SelectorDO selector) {
        publish(new SelectorCreatedEvent(selector, SessionUtil.visitorName()));
    }
    
    /**
     * on selector updated.
     *
     * @param selector selector
     * @param before   before selector
     */
    @Override
    public void onUpdated(final SelectorDO selector, final SelectorDO before) {
        publish(new SelectorChangedEvent(selector, before, EventTypeEnum.SELECTOR_UPDATE, SessionUtil.visitorName()));
    }
    
    /**
     * on selector deleted.
     *
     * @param selector selector
     */
    @Override
    public void onDeleted(final SelectorDO selector) {
        publish(new SelectorChangedEvent(selector, null, EventTypeEnum.SELECTOR_DELETE, SessionUtil.visitorName()));
    }
    
    /**
     * on plugin deleted.
     *
     * @param selectors        selectors
     * @param selectorDataList selectorDataList
     */
    public void onDeleted(final Collection<SelectorDO> selectors, final List<SelectorData> selectorDataList) {
        onDeleted(selectors);
        publisher.publishEvent(new DataChangedEvent(ConfigGroupEnum.SELECTOR, DataEventTypeEnum.DELETE, selectorDataList));
    }
    
    /**
     * on plugin deleted.
     *
     * @param selectors selectors
     */
    @Override
    public void onDeleted(final Collection<SelectorDO> selectors) {
        publish(new BatchSelectorDeletedEvent(selectors, SessionUtil.visitorName()));
    }
    
    /**
     * event.
     *
     * @param event event.
     */
    @Override
    public void publish(final AdminDataModelChangedEvent event) {
        publisher.publishEvent(event);
    }
}
