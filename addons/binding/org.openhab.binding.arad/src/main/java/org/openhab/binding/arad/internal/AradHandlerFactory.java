/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.arad.internal;

import static org.openhab.binding.arad.AradBindingConstants.THING_TYPE_ARAD;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.arad.handler.AradHandler;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link AradHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author y - Initial contribution
 */
@Component(service = ThingHandlerFactory.class, immediate = true, configurationPid = "binding.arad")
public class AradHandlerFactory extends BaseThingHandlerFactory {
    public static String email;
    public static String password;
    public static String deviceId;

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_ARAD);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_ARAD)) {
            return new AradHandler(thing);
        }

        return null;
    }

    @Override
    protected void activate(ComponentContext componentContext) {
        super.activate(componentContext);
        Dictionary<String, Object> properties = componentContext.getProperties();
        email = (String) properties.get("email");
        password = (String) properties.get("password");
        if (deviceId != null) {
            return;
        }
        if (properties.get("device_id") == null || !(properties.get("device_id") instanceof Integer)) {
            deviceId = RandomStringUtils.randomNumeric(16);
        } else {
            deviceId = (properties.get("device_id")) + "";
        }

    }
}
