package org.jukeboxmc.item;

import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.direction.BlockFace;
import org.jukeboxmc.entity.passive.EntityMule;
import org.jukeboxmc.math.Vector;
import org.jukeboxmc.player.Player;

/**
 * @author Kaooot
 * @version 1.0
 */
public class ItemMuleSpawnEgg extends Item {

    public ItemMuleSpawnEgg() {
        super( "minecraft:mule_spawn_egg" );
    }

    @Override
    public boolean interact( Player player, BlockFace blockFace, Vector clickedVector, Block clickedBlock ) {
        EntityMule entityMule = new EntityMule();
        entityMule.setLocation( clickedBlock.getSide( blockFace ).getLocation().add( 0.5f, -entityMule.getEyeHeight(), 0.5f ) );
        entityMule.spawn();

        return true;
    }
}