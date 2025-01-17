package org.jukeboxmc.item;

import org.jukeboxmc.item.behavior.ItemBucketBehavior;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class ItemBucket extends ItemBucketBehavior {

    public ItemBucket() {
        super ( "minecraft:bucket" );
    }

    @Override
    public int getMaxAmount() {
        return 16;
    }
}
