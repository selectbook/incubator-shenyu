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

package org.apache.shenyu.admin.model.event.plugin;

import org.apache.commons.lang3.StringUtils;
import org.apache.shenyu.admin.model.entity.BaseDO;
import org.apache.shenyu.admin.model.entity.PluginDO;
import org.apache.shenyu.admin.model.enums.EventTypeEnum;
import org.apache.shenyu.admin.utils.ListUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BatchPluginDeletedEvent.
 */
public class BatchPluginDeletedEvent extends BatchPluginChangedEvent {
    
    private final List<String> deletedPluginIds;
    
    /**
     * Create a new {@code PluginChangedEvent}.operator is unknown.
     *
     * @param source   Current plugin state
     * @param operator operator
     */
    public BatchPluginDeletedEvent(final Collection<PluginDO> source, final String operator) {
        super(source, null, EventTypeEnum.PLUGIN_DELETE, operator);
        this.deletedPluginIds = ListUtil.map(source, BaseDO::getId);
    }
    
    @Override
    public String buildContext() {
        final String plugins = ((Collection<?>) getSource())
                .stream()
                .map(s -> ((PluginDO) s).getName())
                .collect(Collectors.joining(","));
        return String.format("the plugins[%s] is %s", plugins, StringUtils.lowerCase(getType().getType().toString()));
    }
    
    /**
     * get deleted iss.
     *
     * @return list
     */
    public List<String> getDeletedPluginIds() {
        return deletedPluginIds;
    }
}
