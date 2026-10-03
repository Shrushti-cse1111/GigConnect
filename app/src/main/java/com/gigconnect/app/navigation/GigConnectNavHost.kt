package com.gigconnect.app.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.data.model.*
import com.gigconnect.app.ui.admin.*
import com.gigconnect.app.ui.components.DemoLocationSwitcher
import com.gigconnect.app.ui.components.DemoRoleSwitcher
import com.gigconnect.app.ui.onboarding.*
import com.gigconnect.app.ui.seeker.*
import com.gigconnect.app.ui.worker.*
import com.gigconnect.app.viewmodel.*

@Composable
fun GigConnectNavHost() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()
    val seekerViewModel: SeekerViewModel = viewModel()
    val workerViewModel: WorkerViewModel = viewModel()
    val adminViewModel: AdminViewModel = viewModel()

    var showRoleSwitcher by remember { mutableStateOf(false) }
    var showLocationSwitcher by remember { mutableStateOf(false) }

    val currentRole by appViewModel.currentRole.collectAsState()
    val currentCity by appViewModel.currentCity.collectAsState()
    val isOffline by appViewModel.isOffline.collectAsState()

    // ── Demo overlay sheets ──────────────────────────────────────────────────
    if (showRoleSwitcher) {
        DemoRoleSwitcher(
            currentRole = currentRole,
            onRoleSelected = { role ->
                appViewModel.setRole(role)
                when (role) {
                    UserRole.SEEKER -> navController.navigate(Routes.SEEKER_HOME) { popUpTo(0) }
                    UserRole.WORKER -> navController.navigate(Routes.WORKER_HOME) { popUpTo(0) }
                    UserRole.ADMIN  -> navController.navigate(Routes.ADMIN_DASHBOARD) { popUpTo(0) }
                }
            },
            onDismiss = { showRoleSwitcher = false }
        )
    }

    if (showLocationSwitcher) {
        DemoLocationSwitcher(
            cities = DemoDataProvider.demoCities,
            selectedCity = DemoDataProvider.demoCities.find { it.name == currentCity }
                ?: DemoDataProvider.demoCities.first(),
            onCitySelected = { city ->
                appViewModel.setCity(city.name)
                seekerViewModel.loadWorkers(city.name)
            },
            onDismiss = { showLocationSwitcher = false }
        )
    }

    // ── Navigation Graph ──────────────────────────────────────────────────────
    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {

        // ── ONBOARDING ────────────────────────────────────────────────────────
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onGetStarted = { navController.navigate(Routes.LOCATION_SELECTION) },
                onLogin = { navController.navigate(Routes.AUTH) }
            )
        }

        composable(Routes.AUTH) {
            AuthScreen(
                appViewModel = appViewModel,
                onAuthSuccess = { role ->
                    appViewModel.setRole(role)
                    when (role) {
                        UserRole.SEEKER -> navController.navigate(Routes.SEEKER_HOME) { popUpTo(Routes.WELCOME) { inclusive = true } }
                        UserRole.WORKER -> navController.navigate(Routes.WORKER_HOME) { popUpTo(Routes.WELCOME) { inclusive = true } }
                        UserRole.ADMIN  -> navController.navigate(Routes.ADMIN_DASHBOARD) { popUpTo(Routes.WELCOME) { inclusive = true } }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.LOCATION_SELECTION) {
            LocationSelectionScreen { city, _ ->
                appViewModel.setCity(city)
                navController.navigate(Routes.ROLE_SELECTION)
            }
        }

        composable(Routes.ROLE_SELECTION) {
            RoleSelectionScreen { role ->
                appViewModel.setRole(role)
                when (role) {
                    UserRole.SEEKER -> navController.navigate(Routes.SEEKER_HOME) { popUpTo(Routes.WELCOME) { inclusive = true } }
                    UserRole.WORKER -> navController.navigate(Routes.WORKER_HOME) { popUpTo(Routes.WELCOME) { inclusive = true } }
                    UserRole.ADMIN  -> navController.navigate(Routes.ADMIN_DASHBOARD) { popUpTo(Routes.WELCOME) { inclusive = true } }
                }
            }
        }

        // ── SEEKER ────────────────────────────────────────────────────────────
        composable(Routes.SEEKER_HOME) {
            SeekerHomeScreen(
                cityName = currentCity,
                seekerViewModel = seekerViewModel,
                onServiceSelected = { service ->
                    seekerViewModel.initServiceRequest(service, currentCity)
                    navController.navigate(Routes.SERVICE_REQUEST)
                },
                onWorkerTapped = { worker ->
                    seekerViewModel.selectWorkerById(worker.id)
                    navController.navigate(Routes.workerProfile(worker.id))
                },
                onBookingsClick = { navController.navigate(Routes.BOOKINGS_LIST) },
                onMessagesClick = {},
                onProfileClick = { navController.navigate(Routes.SEEKER_PROFILE) },
                onRoleSwitchClick = { showRoleSwitcher = true },
                onLocationClick = { showLocationSwitcher = true },
                isOffline = isOffline
            )
        }

        composable(Routes.SERVICE_REQUEST) {
            ServiceRequestScreen(
                seekerViewModel = seekerViewModel,
                cityName = currentCity,
                onProceedToMatch = {
                    navController.navigate(Routes.SMART_MATCH)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.WORKER_LIST,
            arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
        ) { backStack ->
            val serviceId = backStack.arguments?.getString("serviceId") ?: ""
            WorkerListScreen(
                serviceId = serviceId,
                cityName = currentCity,
                seekerViewModel = seekerViewModel,
                onWorkerSelected = { worker ->
                    seekerViewModel.selectWorkerById(worker.id)
                    navController.navigate(Routes.workerProfile(worker.id))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.WORKER_PROFILE,
            arguments = listOf(navArgument("workerId") { type = NavType.StringType })
        ) { backStack ->
            val workerId = backStack.arguments?.getString("workerId") ?: ""
            val worker = DemoDataProvider.getWorkerById(workerId)
            if (worker != null) {
                WorkerProfileScreen(
                    worker = worker,
                    onBook = { seekerViewModel.setSelectedWorker(worker); navController.navigate(Routes.SMART_MATCH) },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.SMART_MATCH) {
            SmartMatchScreen(
                seekerViewModel = seekerViewModel,
                onWorkerSelected = { navController.navigate(Routes.BOOKING) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.BOOKING) {
            BookingScreen(
                seekerViewModel = seekerViewModel,
                onConfirm = { navController.navigate(Routes.BOOKING_CONFIRMATION) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.BOOKING_CONFIRMATION) {
            BookingConfirmationScreen(
                seekerViewModel = seekerViewModel,
                onTrack = { navController.navigate(Routes.LIVE_TRACKING) },
                onHome = { navController.navigate(Routes.SEEKER_HOME) { popUpTo(Routes.SEEKER_HOME) { inclusive = true } } }
            )
        }

        composable(Routes.LIVE_TRACKING) {
            LiveTrackingScreen(
                seekerViewModel = seekerViewModel,
                onPayment = { navController.navigate(Routes.PAYMENT) },
                onDispute = { navController.navigate(Routes.DISPUTE) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PAYMENT) {
            PaymentScreen(
                seekerViewModel = seekerViewModel,
                onSuccess = { navController.navigate(Routes.PAYMENT_SUCCESS) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PAYMENT_SUCCESS) {
            PaymentSuccessScreen(
                seekerViewModel = seekerViewModel,
                onRate = { navController.navigate(Routes.RATING) },
                onHome = { navController.navigate(Routes.SEEKER_HOME) { popUpTo(Routes.SEEKER_HOME) { inclusive = true } } }
            )
        }

        composable(Routes.RATING) {
            RatingScreen(
                seekerViewModel = seekerViewModel,
                onDone = { navController.navigate(Routes.SEEKER_HOME) { popUpTo(Routes.SEEKER_HOME) { inclusive = true } } }
            )
        }

        composable(Routes.BOOKINGS_LIST) {
            BookingsListScreen(
                seekerViewModel = seekerViewModel,
                onTrackBooking = { booking ->
                    seekerViewModel.loadBookingForTracking(booking)
                    navController.navigate(Routes.LIVE_TRACKING)
                },
                onDisputeBooking = { booking ->
                    seekerViewModel.loadBookingForTracking(booking)
                    navController.navigate(Routes.DISPUTE)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.DISPUTE) {
            DisputeScreen(
                seekerViewModel = seekerViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.SEEKER_PROFILE) {
            SeekerProfileScreen(onBack = { navController.popBackStack() })
        }

        // ── WORKER ─────────────────────────────────────────────────────────────
        composable(Routes.WORKER_HOME) {
            WorkerHomeScreen(
                workerViewModel = workerViewModel,
                onJobsClick = { navController.navigate(Routes.JOBS_LIST) },
                onEarningsClick = { navController.navigate(Routes.EARNINGS) },
                onSkillsClick = { navController.navigate(Routes.SKILL_UP) },
                onProfileClick = { navController.navigate(Routes.WORKER_PASSPORT) },
                onJobDetailClick = { job: Job ->
                    workerViewModel.selectJob(job)
                    navController.navigate(Routes.jobDetail(job.id))
                },
                onRoleSwitchClick = { showRoleSwitcher = true },
                onEquityClick = { navController.navigate(Routes.EQUITY_WALLET) },
                onSafetyClick = { navController.navigate(Routes.SAFETY_CENTER) }
            )
        }

        composable(Routes.JOBS_LIST) {
            JobsListScreen(
                workerViewModel = workerViewModel,
                onJobDetail = { job: Job ->
                    workerViewModel.selectJob(job)
                    navController.navigate(Routes.jobDetail(job.id))
                },
                onHomeClick = { navController.navigate(Routes.WORKER_HOME) { popUpTo(Routes.WORKER_HOME) { inclusive = true } } },
                onEarningsClick = { navController.navigate(Routes.EARNINGS) },
                onProfileClick = { navController.navigate(Routes.WORKER_PASSPORT) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.JOB_DETAIL,
            arguments = listOf(navArgument("jobId") { type = NavType.StringType })
        ) { backStack ->
            val jobId = backStack.arguments?.getString("jobId") ?: ""
            JobDetailScreen(
                jobId = jobId,
                workerViewModel = workerViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.EARNINGS) {
            EarningsScreen(workerViewModel = workerViewModel, onEquityClick = { navController.navigate(Routes.EQUITY_WALLET) }, onBack = { navController.popBackStack() })
        }

        composable(Routes.EQUITY_WALLET) {
            EquityWalletScreen(workerViewModel = workerViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.SKILL_UP) {
            SkillUpScreen(workerViewModel = workerViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.VOICE_ASSISTANT) {
            VoiceAssistantScreen(workerViewModel = workerViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.SAFETY_CENTER) {
            SafetyCenterScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.WORKER_PASSPORT) {
            WorkerPassportScreen(workerViewModel = workerViewModel, onVoiceClick = { navController.navigate(Routes.VOICE_ASSISTANT) }, onBack = { navController.popBackStack() })
        }

        // ── ADMIN ──────────────────────────────────────────────────────────────
        composable(Routes.ADMIN_DASHBOARD) {
            AdminDashboardScreen(
                adminViewModel = adminViewModel,
                onDemandClick = { navController.navigate(Routes.DEMAND_INTELLIGENCE) },
                onFairMatchClick = { navController.navigate(Routes.FAIR_MATCH) },
                onWorkersClick = { navController.navigate(Routes.WORKER_MANAGEMENT) },
                onWelfareClick = { navController.navigate(Routes.WELFARE) },
                onPanchayatClick = { navController.navigate(Routes.DIGITAL_PANCHAYAT) },
                onArchitectureClick = { navController.navigate(Routes.ARCHITECTURE) },
                onRoleSwitchClick = { showRoleSwitcher = true }
            )
        }

        composable(Routes.DEMAND_INTELLIGENCE) {
            DemandIntelligenceScreen(adminViewModel = adminViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.FAIR_MATCH) {
            FairMatchScreen(adminViewModel = adminViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.WORKER_MANAGEMENT) {
            WorkerManagementScreen(adminViewModel = adminViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.WELFARE) {
            WelfareScreen(adminViewModel = adminViewModel, onBack = { navController.popBackStack() })
        }

        composable(Routes.DIGITAL_PANCHAYAT) {
            DigitalPanchayatScreen(adminViewModel = adminViewModel, onBack = { navController.popBackStack() }, onVotingClick = { navController.navigate(Routes.COOPERATIVE_VOTING) })
        }

        composable(Routes.COOPERATIVE_VOTING) {
            DigitalPanchayatScreen(adminViewModel = adminViewModel, onBack = { navController.popBackStack() }, onVotingClick = {})
        }

        composable(Routes.ARCHITECTURE) {
            ArchitectureScreen(onBack = { navController.popBackStack() })
        }
    }
}
