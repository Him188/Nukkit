package cn.nukkit.entity;

/**
 * @author Adam Matthew
 */
public interface EntityInteractable extends IEntity {

    // Todo: Passive entity?? i18n and boat leaving text
    String getInteractButtonText();

    boolean canDoInteraction();

}
