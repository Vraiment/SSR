package vraiment.sage.ssr.view.dsl

import javafx.scene.control.TreeItem

/*
 This file contains a small "DSL" (Domain Specific Language) for JavaFX.
 */

/**
 * Annotation to mark functions that as part of the DSL.
 */
@DslMarker
annotation class JavaFx

/**
 * Creates a new TreeItem and uses the given block to configure it.
 *
 * @param block The block to configure the TreeItem.
 *
 * @return A TreeItem with configured according to the input block.
 */
@JavaFx
fun <T> treeItem(block: TreeItem<T>.() -> Unit): TreeItem<T> = TreeItem<T>()
    .apply(block)


/**
 * Adds a new tree item to the given tree and uses the given block to configure it.
 *
 * @param block The block to configure the TreeItem.
 *
 * @return A TreeItem (children of the given TreeItem) configured according to the input block.
 */
@JavaFx
fun <T> TreeItem<T>.treeItem(block: TreeItem<T>.() -> Unit = {}): TreeItem<T> = TreeItem<T>()
    .apply(block)
    .also { children.add(it) }
