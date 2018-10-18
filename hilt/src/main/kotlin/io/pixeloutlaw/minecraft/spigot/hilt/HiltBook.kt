package io.pixeloutlaw.minecraft.spigot.hilt

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

class HiltBook(bookType: BookType) : ItemStack(bookType.material) {
    fun addPage(vararg pages: String) {
        bookMeta()?.addPage(*pages)
    }

    fun getAuthor(): String? =
        bookMeta()?.author

    fun getGeneration(): BookMeta.Generation? =
        bookMeta()?.generation

    fun getPage(page: Int): String? =
        bookMeta()?.getPage(page)

    fun getPageCount(): Int? =
        bookMeta()?.pageCount

    fun getPages(): List<String> =
        bookMeta()?.pages ?: emptyList()

    fun hasAuthor(): Boolean? =
        bookMeta()?.hasAuthor()

    fun hasGeneration(): Boolean? =
        bookMeta()?.hasGeneration()

    fun hasPages(): Boolean? =
        bookMeta()?.hasPages()

    fun hasTitle(): Boolean? =
        bookMeta()?.hasTitle()

    fun setAuthor(author: String) {
        bookMeta()?.author = author
    }

    fun setGeneration(generation: BookMeta.Generation) {
        bookMeta()?.generation = generation
    }

    fun setPage(page: Int, data: String) {
        bookMeta()?.setPage(page, data)
    }

    fun setPages(vararg pages: String) {
        bookMeta()?.setPages(*pages)
    }

    fun setPages(pages: List<String>) {
        bookMeta()?.pages = pages
    }

    fun setTitle(title: String) {
        bookMeta()?.title = title
    }

    private fun bookMeta() = (this.getOrCreateItemMeta() as? BookMeta)
}