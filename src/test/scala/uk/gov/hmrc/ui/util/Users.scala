/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.util

object Users {

  sealed abstract class UserTypes(val affinity: String, val slug: String, val landingPage: String, val slug2: String)

  object UserTypes {

    case object Individual
        extends UserTypes(affinity = "Individual", slug = "ind", landingPage = "aboutyou", slug2 = "")

    case object Partnership
        extends UserTypes(affinity = "Organisation", slug = "org/partnership", landingPage = "aboutyourorg", slug2 = "")

    case object Organisation
        extends UserTypes(affinity = "Organisation", slug = "org", landingPage = "aboutyourorg", slug2 = "contact/paye")

    case object Agent_Trust
        extends UserTypes(affinity = "Agent", slug = "org/trust", landingPage = "aboutyourclient", slug2 = "")

  }

  sealed abstract class LoginTypes(
    val username: String = "",
    val identifier: String,
    val clientIdentifier: String = "",
    val enrolmentKey: String,
    val enrolmentId: String,
    val enrolmentId2: String = "",
    val enrolmentValue2: String = "",
    val credId: String = "",
    val confidenceLevel: String = "50",
    val nino: String = "",
    val saPreset: String = ""
  )

  object LoginTypes {

    case object HASDIRECT
        extends LoginTypes(
          username = "",
          identifier = "",
          enrolmentKey = "",
          enrolmentId = "",
          nino = "",
          confidenceLevel = "250",
          saPreset = ""
        )

  }

}
