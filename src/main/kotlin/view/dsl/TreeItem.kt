package vraiment.sage.ssr.view.dsl

import javafx.scene.control.TreeItem

/*
 This file contains a small "DSL" (Domain Specific Language) to create TreeItems for a TreeView:

 - Use the function `treeItem` to create a new TreeItem.
 - To add children to a TreeItem use the member children: `treeItem(value) { children { ... } } `
 */

/**
 * Creates a new TreeItem with the input value, and the configuration from the input block.
 *
 * @param value The value for the TreeItem
 * @param block The block to configure the TreeItem
 *
 * @return A TreeItem with the input value and configured according to the input block.
 */
fun <T> treeItem(value: T, block: TreeItemBuilder<T>.() -> Unit) = TreeItemBuilder<T>()
        .also { it.value = value }
        .apply(block)
        .build()

/**
 * Class to build a and configure a TreeItem object.
 */
class TreeItemBuilder<T> {
    /**
     * The value of the tree item object.
     */
    var value: T? = null

    /**
     * Adds any TreeItem provided by the input block to the TreeItem that will be build.
     *
     * @param block The block to configure
     *
     * @return The same instance of TreeItemBuilder<T>.
     */
    fun children(block: TreeItems<T>.() -> Unit) = TreeItems<T>()
            .apply(block)
            .also { children.addAll(it) }
            .let { this }

    /**
     * Builds a TreeItem instance with the values from the builder.
     *
     * @return A TreeItem<T> with the values from the builder.
     */
    fun build(): TreeItem<T> {
        val result = TreeItem<T>()

        result.value = value
        result.children.addAll(children)

        return result
    }

    private val children = mutableListOf<TreeItem<T>>()
}

/**
 * Helper class to allow create a list of TreeItems<T>.
 */
class TreeItems<T> : ArrayList<TreeItem<T>>() {
    /**
     * Adds tree item with the given input value to the list of tree items.
     *
     * @param value The value for the TreeItem.
     *
     * @return The created TreeItem.
     */
    fun treeItem(value: T) = TreeItem(value).also { add(it) }

    /**
     * Adds a tree item with the given input value to the list of tree items and configures it using the input block.
     *
     * @param value The value for the TreeItem.
     * @param block The block to configure the TreeItem.
     *
     * @return The created TreeItem.
     */
    fun treeItem(value: T, block: TreeItemBuilder<T>.() -> Unit) = TreeItemBuilder<T>()
            .also { it.value = value }
            .apply(block)
            .build()
            .also { add(it) }
}
