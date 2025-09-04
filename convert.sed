s/BipedEntityModel/HumanoidModel/g
s/StringIdentifiable/StringRepresentable/g
s/Representable.Codec/Representable.EnumCodec/g
s/.createCodec/.fromEnum/g
s/(stem)/(headBlock)/g
s/getGrowthLength(/getBlocksToGrowWhenBonemealed(/g
s/getStem(/getHeadBlock(/g
s/Shapes.fullCube(/Shapes.block(/g
s/AbstractPlantBlock/GrowingPlantBodyBlock/g
s/Block(settings: Settings/Block(settings: Properties/g
s/getWanderTarget(/getPosition(/g
s/util.Identifier/resources.ResourceLocation/g
s/ofCenter/atCenterOf/g
s/ EntityData?/ SpawnGroupData?/g
s/ EntityPose / Pose /g
s/ EntityPose, / Pose, /g
s/ LookAroundGoal/ RandomLookAroundGoal/g
s/ movementSpeed,/ speed,/g
s/ pitch / xRot /g
s/ PlayerEntity/Player/g
s/ pos.down(/ pos.below(/g
s/ pos.up(/ pos.above(/g
s/ Random)/ RandomSource)/g
s/ yaw / yRot /g
s/.cuboid/.cube/g
s/.EntityRendererFactory/.EntityRendererProvider/g
s/.isIn(/.`is`(/g
s/).cube/).addBox/g
s/\.uv(/\.texOffs(/g
s/\.Identifier/\.ResourceLocation/g
s/ Identifier/ ResourceLocation/g
s/\WModelData/MeshDefinition/g
s/AbstractPlantStemBlock/GrowingPlantHeadBlock/g
#'s/ActivationRule/Sensitivity/g
s/ActiveTargetGoal/NearestAttackableTargetGoal/g
s/addChild/addOrReplaceChild/g
s/AdvancementFrame/FrameType/g
s/AquaticMoveControl/SmoothSwimmingMoveControl/g
s/BlockPlacementContext/BlockPlaceContext/g
s/BlockView/BlockGetter/g
s/breakInstantly/instabreak/g
s/canBreatheInWater/canBreatheUnderwater/g
s/canFillWithFluid/canPlaceLiquid/g
s/canImmediatelyDespawn/removeWhenFarAway/g
s/canPathfindThrough/isPathfindable/g
s/canPlaceAt/canSurvive/g
s/canPlantOnTop/mayPlantOn/g
s/canStart(/canUse(/g
s/CODEC.byId/CODEC.byName/g
s/createCuboidShape/box/g
s/createIdToValueFunction/continuous/g
s/dataTracker/entityData/g
s/DataTracker/SynchedEntityData/g
s/DeadCoralBlock/BaseCoralPlantBlock/g
s/DeadCoralFanBlock/BaseCoralFanBlock/g
s/DeadCoralWallFanBlock/BaseCoralWallFanBlock/g
s/DefaultAttributeContainer/AttributeSupplier/g
s/defaultState/defaultBlockState()/g
s/Dilation/CubeDeformation/g
s/DoubleBush/DoublePlant/g
s/EntityAttributes/Attributes/g
s/TrackedDataHandlerRegistry/EntityDataSerializers/g
s/entryOf/getHolderOrThrow/g
s/EscapeDangerGoal/PanicGoal/g
s/Fertilizable/BonemealableBlock/g
s/FluidFillable/LiquidBlockContainer/g
s/fluidState.level/fluidState.amount/g
s/GENERIC_//g
s/getActiveEyeHeight/getStandingEyeHeight/g
s/getEntityTranslucent/entityTranslucent/g
s/getLimitPerChunk/getSpawnClusterSize/g
s/getOutlineShape/getShape/g
s/getPickStack/getCloneItemStack/g
s/getPlacementState/getStateForPlacement/g
s/getPlant(/getBodyBlock(/g
s/getStateForNeighborUpdate/updateShape/g
s/getStill/getSource/g
s/getTickRate/getTickDelay/g
s/getWanderTarget(/getPosition(/g
s/goalSelector.add(/goalSelector.addGoal(/g
s/.grow(/.performBonemeal(/g
s/HostileEntity/Monster/g
s/HybridAquatic.MOD_ID/Constants.MOD_ID/g
s/Identifier/ResourceLocation/g
s/initDataTracker/defineSynchedData/g
s/initGoals/registerGoals/g
s/initialize(/finalizeSpawn(/g
s/\.Instrument/.NoteBlockInstrument/g
s/.isAiDisabled/.isNoAi/g
s/isAngryAt/isPreventingPlayerRest/g
s/isFertilizable/isValidBonemealTarget/g
s/isClient /isClientSide /g
s/isClient)/isClientSide)/g
s/isPushedByFluids/isPushedByFluid/g
s/isSideSolidFullSquare/isFaceSturdy/g
s/isSkyVisibleAllowingSea/canSeeSkyFromBelowWater/g
s/isSpawnDark/isDarkEnoughToSpawn/g
s/isSubmergedInWater/IsUnderWater/g
s/IsUnderWater/isUnderWater/g
s/isWater(/isWaterAt(/g
s/isWet/isInWaterRainOrBubble/g
s/ItemPlacementContext/BlockPlaceContext/g
s/LevelAccess /LevelAccessor /g
s/LocalDifficulty/DifficultyInstance/g
s/ LookAtEntityGoal/ LookAtPlayerGoal/g
s/MathHelper/Mth/g
s/mob.world/mob.level()./g
s/ModelPartBuilder/CubeListBuilder/g
s/ModelTransform.NONE/PartPose.ZERO/g
s/ModelTransform.pivot/PartPose.offset/g
s/ModelTransformationMode/ItemDisplayContext/g
s/MoveIntoWaterGoal/TryFindWaterGoal/g
s/NavigationType/PathComputationType/g
s/NbtCompound/CompoundTag/g
s/nextBetween/nextIntBetweenInclusive/g
s/noCollision/noCollission/g
s/nonOpaque/noOcclusion/g
s/ofCenter/atCenterOf/g
s/ofFloored/containing/g
s/OnKilledCriterion/KilledTrigger/g
s/onStartedTrackingBy/startSeenByPlayer/g
s/onStoppedTrackingBy/stopSeenByPlayer/g
s/OutOfBoundsHandling/OutOfBoundsStrategy/g
s/PathAwareEntity/PathfinderMob/g
s/PathNodeType/BlockPathTypes/g
s/ PlantBlock/ BushBlock/g
s/PlayerEntity/Player/g
s/pos\.up/pos\.above/g
s/randomDisplayTick/animateTick/g
s/readCustomDataFromNbt/readAdditionalSaveData/g
s/registerData/defineId/g
s/RenderLayer/RenderType/g
s/requiresTool/requiresCorrectToolForDrops/g
s/scheduleFluidTick/scheduleTick/g
s/ServerWorldAccess /ServerLevelAccessor /g
s/ServerWorldAccess,/ServerLevelAccessor,/g
s/ServerWorld/ServerLevel/g
s/setNearbySongPlaying/setRecordPlayingNearby/g
s/setPathfindingPenalty/setPathfindingMalus/g
s/ShapeContext/CollisionContext/g
s/shouldSwimInFluids/isAffectedByFluids/g
s/SoundEvents\.ENTITY/SoundEvents./g
s/SpawnReason/MobSpawnType/g
s/stairsBlock/stairBlock/g
s/startTracking(/define(/g
s/state.isOf(/state.`is`(/g
s/StatusEffect/MobEffect/g
s/ SwimAroundGoal/ RandomSwimmingGoal/g
s/ SwimNavigation/ WaterBoundPathNavigation/g
s/targetSelector.add(/targetSelector.addGoal(/g
s/TexturedModel.of/LayerDefinition.create/g
s/TexturedModelData/TexturedModel/g
s/this.air /this.airSupply/g
s/tickHandSwing/updateSwingTime/g
s/tickMovement/aiStep/g
s/tickWaterBreathingAir/handleAirSupply/g
s/TrackedData/EntityDataAccessor/g
s/tryAttack/doHurtTarget/g
s/tryFillWithFluid/placeLiquid/g
s/ValueLists/ByIdMap/g
s/ WanderAroundGoal/ RandomStrollGoal/g
s/ WaterCreatureEntity/ WaterAnimal/g
s/Waterloggable/SimpleWaterloggedBlcok/g
s/WorldAccess,/LevelAccessor,/g
s/WorldAccess /LevelAccessor /g
s/WorldView/LevelReader/g
s/writeCustomDataToNbt/addAdditionalSaveData/g
s/YawAdjustingLookControl/SmoothSwimmingLookControl/g
s/minecraft\.client\.render\./minecraft.client.renderer./g
s/chooseStemState(/canGrowInto(/g
s/ctx.blockPos)/ctx.clickedPos)/g
s/ctx.world.get/ctx.level.get/g
s/VoxelShapes/Shapes/g
s/world: World)/world: Level)/g
s/world: World /world: Level /g
s/world: World,/world: Level,/g
s/VertexConsumerProvider/MultiBufferSource/g
s/MatrixStack/PoseStack/g
s/world: World /world: Level /g
s/ onEntityCollision/ entityInside/g
s/ BlockEntityProvider/ EntityBlock/g
s/Blcok/Block/g
s/ onBreak/ playerWillDestroy/g
s/.onBreak/.playerWillDestroy/g
s/DO_TILE_DROPS/RULE_DOBLOCKDROPS/g
s/.getFace(/.getFaceShape(/g
s/ mayPlantOn(/ mayPlaceOn(/g
s/\.state.get(/.state.getValue(/g
s/BlockWithEntity/BaseEntityBlock/g
s/\.checkType/\.createTickerHelper/g
s/state.get(/state.getValue(/g
s/\.isOf(/.`is`(/g
s/BlockRenderType/RenderShape/g
s/getRenderType(state/getRenderShape(state/g
s/ FeatureContext/ FeaturePlaceContext /g
s/ FeatureConfig/ FeatureConfiguration /g
s/structureWorldAccess/structureLevelAccessor/g
s/Type.OCEAN_FLOOR/Types.OCEAN_FLOOR/g
s/BlockStateProvider.TYPE_CODEC/BlockStateProvider.CODEC/g
s/context\.world/context.level()/g
s/context\.origin/context.origin()/g
s/context\.random/context.random()/g
s/createBlockEntity/newBlockEntity/g
s/appendProperties/createBlockStateDefinition/g
s/StateManager/StateDefinition/g
