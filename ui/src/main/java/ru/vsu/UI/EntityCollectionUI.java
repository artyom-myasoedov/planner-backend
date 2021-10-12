package ru.vsu.UI;

import java.util.Collection;

public interface EntityCollectionUI extends UI {

    <T> void showEntityCollection(Collection<T> entities);
}
