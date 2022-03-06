package org.jukeboxmc.world.chunk;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.jukeboxmc.block.Block;
import org.jukeboxmc.block.BlockPalette;
import org.jukeboxmc.block.BlockRedstoneOre;
import org.jukeboxmc.block.BlockType;
import org.jukeboxmc.blockentity.BlockEntity;
import org.jukeboxmc.utils.BinaryStream;
import org.jukeboxmc.utils.Utils;
import org.jukeboxmc.world.palette.integer.IntPalette;

import java.util.Collection;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class SubChunk {

    private static final int AIR_RUNTIME_ID = BlockType.AIR.getBlock().getRuntimeId();

    @Getter
    private final int y;

    public IntPalette[] blocks;
    private final Int2ObjectMap<BlockEntity> blockEntities;

    public SubChunk( int subChunkY ) {
        this.y = subChunkY;

        this.blocks = new IntPalette[Chunk.CHUNK_LAYERS];
        this.blockEntities = new Int2ObjectOpenHashMap<>();
        for ( int layer = 0; layer < Chunk.CHUNK_LAYERS; layer++ ) {
            this.blocks[layer] = new IntPalette( AIR_RUNTIME_ID );
        }
    }

    public void setBlock( int index, int layer, int runtimeId ) {
        this.blocks[layer].set( index, runtimeId );
    }

    public void setBlock( int x, int y, int z, int layer, int runtimeId ) {
        this.blocks[layer].set( Utils.getIndex( x, y, z ), runtimeId );
    }

    public void setBlock( int x, int y, int z, int layer, Block block ) {
        this.blocks[layer].set( Utils.getIndex( x, y, z ), block.getRuntimeId() );
    }

    public int getRuntimeId( int x, int y, int z, int layer ) {
        return this.blocks[layer].get( Utils.getIndex( x, y, z ) );
    }

    public Block getBlock( int x, int y, int z, int layer ) {
        return BlockPalette.RUNTIME_TO_BLOCK.get( this.blocks[layer].get( Utils.getIndex( x, y, z ) ) ).clone();
    }

    public void setBlockEntity( int x, int y, int z, BlockEntity blockEntity ) {
        this.blockEntities.put( Utils.getIndex( x, y, z ), blockEntity );
    }

    public BlockEntity getBlockEntity( int x, int y, int z ) {
        return this.blockEntities.get( Utils.getIndex( x, y, z ) );
    }

    public void removeBlockEntity( int x, int y, int z ) {
        this.blockEntities.remove( Utils.getIndex( x, y, z ) );
    }

    public Collection<BlockEntity> getBlockEntities() {
        return this.blockEntities.values();
    }

    public void writeToNetwork( BinaryStream buffer ) {
        buffer.writeByte( 8 );
        buffer.writeByte( Chunk.CHUNK_LAYERS );

        for ( int layer = 0; layer < Chunk.CHUNK_LAYERS; layer++ )
            this.blocks[layer].writeToNetwork( buffer, runtimeId -> runtimeId );
    }

}