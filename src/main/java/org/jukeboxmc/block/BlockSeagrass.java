package org.jukeboxmc.block;

import org.jukeboxmc.item.ItemSeagrass;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class BlockSeagrass extends Block {

    public BlockSeagrass() {
        super( "minecraft:seagrass" );
    }

    @Override
    public ItemSeagrass toItem() {
        return new ItemSeagrass();
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.SEAGRASS;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    public void setSeaGrassType( SeaGrassType seaGrassType ) {
        this.setState( "sea_grass_type",seaGrassType.name().toLowerCase() );
    }

    public SeaGrassType getSeaGrassType() {
        return this.stateExists( "sea_grass_type" ) ? SeaGrassType.valueOf( this.getStringState( "sea_grass_type" ) ) : SeaGrassType.DEFAULT;
    }

    public enum SeaGrassType {
        DEFAULT,
        DOUBLE_TOP,
        DOUBLE_BOT
    }
}
