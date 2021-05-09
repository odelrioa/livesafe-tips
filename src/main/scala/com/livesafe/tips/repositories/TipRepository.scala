package com.livesafe.tips.repositories

import com.livesafe.tips.Repository
import com.livesafe.tips.models.Tip

class TipRepository extends Repository[Tip](_.id)
