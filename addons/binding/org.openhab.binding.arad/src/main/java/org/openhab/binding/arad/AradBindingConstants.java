/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.arad;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link AradBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author y - Initial contribution
 */
public class AradBindingConstants {

    private static final String BINDING_ID = "arad";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_ARAD = new ThingTypeUID(BINDING_ID, "arad");

    public final static String CONFIG_REFRESHTIME = "refresh_time_in_min";

    public final static String ARAD_KEY = "aradKey";

    // List of all Channel ids
    public static final String CHANNEL_LAST_READ = "last_read";
    public static final String CHANNEL_MONTHLY_CURRENT_CONSUMPTION = "monthly_current_consumption";
    public static final String CHANNEL_LOW_PRICE_CONSUMPTION_MC = "low_price_consumption_mc";
    public static final String CHANNEL_CURRENT_CONSUMPTION = "daily_current_consumption";

}
