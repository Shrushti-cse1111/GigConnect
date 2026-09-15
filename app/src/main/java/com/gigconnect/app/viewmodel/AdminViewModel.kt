package com.gigconnect.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AdminViewModel : ViewModel() {

    val adminStats = DemoDataProvider.adminStats
    val demandForecasts = DemoDataProvider.demandForecasts
    val welfareStats = DemoDataProvider.welfareStats
    val allWorkers = DemoDataProvider.puneWorkers

    private val _grievances = MutableStateFlow(DemoDataProvider.grievanceCases)
    val grievances: StateFlow<List<GrievanceCase>> = _grievances.asStateFlow()

    private val _proposals = MutableStateFlow(DemoDataProvider.votingProposals)
    val proposals: StateFlow<List<VotingProposal>> = _proposals.asStateFlow()

    private val _fairMatchCandidates = MutableStateFlow(DemoDataProvider.getFairMatchCandidates())
    val fairMatchCandidates: StateFlow<List<FairMatchCandidate>> = _fairMatchCandidates.asStateFlow()

    private val _workerFilter = MutableStateFlow(VerificationStatus.VERIFIED)
    val workerFilter: StateFlow<VerificationStatus> = _workerFilter.asStateFlow()

    val filteredWorkers: StateFlow<List<Worker>> = combine(_workerFilter, flowOf(allWorkers)) { filter, workers ->
        when (filter) {
            VerificationStatus.VERIFIED -> workers.filter { it.verificationStatus == VerificationStatus.VERIFIED }
            VerificationStatus.PENDING -> workers.filter { it.verificationStatus == VerificationStatus.PENDING }
            VerificationStatus.NEEDS_REVIEW -> workers.filter { it.verificationStatus == VerificationStatus.NEEDS_REVIEW }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), allWorkers)

    fun setWorkerFilter(status: VerificationStatus) {
        _workerFilter.value = status
    }

    fun vote(proposalId: String, choice: VoteChoice) {
        viewModelScope.launch {
            val updated = _proposals.value.map { p ->
                if (p.id == proposalId && p.userVote == null) {
                    p.copy(
                        approveVotes = if (choice == VoteChoice.APPROVE) p.approveVotes + 1 else p.approveVotes,
                        rejectVotes = if (choice == VoteChoice.REJECT) p.rejectVotes + 1 else p.rejectVotes,
                        userVote = choice
                    )
                } else p
            }
            _proposals.value = updated
        }
    }

    fun raiseGrievance(category: String, description: String) {
        val newCase = GrievanceCase(
            id = "G${(100..999).random()}",
            raisedBy = "Demo Worker",
            category = category,
            description = description,
            status = "Pending",
            raisedDate = "Today"
        )
        _grievances.value = listOf(newCase) + _grievances.value
    }
}
