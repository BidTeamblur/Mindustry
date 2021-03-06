package io.anuke.mindustry.world.blocks.defense.turrets;

import io.anuke.mindustry.entities.bullet.BulletType;
import io.anuke.mindustry.world.Tile;

public abstract class PowerTurret extends CooledTurret{
    protected BulletType shootType;
    protected float powerUse = 1f;

    public PowerTurret(String name){
        super(name);
        hasPower = true;
    }

    @Override
    public void init(){
        consumes.powerCond(powerUse, entity -> ((TurretEntity)entity).target != null);
        super.init();
    }

    @Override
    public BulletType useAmmo(Tile tile){
        //nothing used directly
        return shootType;
    }

    @Override
    public boolean hasAmmo(Tile tile){
        //you can always rotate, but never shoot if there's no power
        return true;
    }

    @Override
    public BulletType peekAmmo(Tile tile){
        return shootType;
    }

    @Override
    protected float baseReloadSpeed(Tile tile){
        return tile.isEnemyCheat() ? 1f : tile.entity.power.satisfaction;
    }
}
