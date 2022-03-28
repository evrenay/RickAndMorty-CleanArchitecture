package com.evren.rickandmorty.util.ext

import androidx.databinding.ViewDataBinding


fun <T : ViewDataBinding> T.executeWithAction(action: T.() -> Unit) {
    action()
    executePendingBindings()
}
