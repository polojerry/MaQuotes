package com.pol0.domain.usecases

interface FlowBaseUseCase<in Params, out T> {
    operator fun invoke(params: Params): T
}